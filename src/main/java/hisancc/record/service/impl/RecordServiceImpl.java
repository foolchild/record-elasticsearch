package hisancc.record.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import hisancc.record.entity.DtRecordInfo;
import hisancc.record.factory.RestHighLevelClientSingletonFactory;
import hisancc.record.mapper.RecordInfoMapper;
import hisancc.record.service.RecordService;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * by zh
 * 2019/11/25
 **/
@Service
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordInfoMapper recordInfoMapper;

    @Resource
    ObjectMapper objectMapper;

    private RestHighLevelClient client = RestHighLevelClientSingletonFactory.getInstance();

    @Override
    public void syncRecord(){
        List<DtRecordInfo> list = recordInfoMapper.selectAll();
        try {
            BulkRequest request = new BulkRequest();
            for (DtRecordInfo record: list) {
                request.add(new IndexRequest("record_info").id(record.getRecordId())
                        .source(objectMapper.writeValueAsString(record), XContentType.JSON));
            }
            request.timeout(TimeValue.timeValueMinutes(2));
            client.bulk(request, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSentence(String recordId, List<Map> list) {
        try {
            Map map = new HashMap();
            map.put("sentences", list);
            UpdateRequest updateRequest = new UpdateRequest("record_info", recordId).doc(map);
            updateRequest.timeout(TimeValue.timeValueMinutes(2));
            client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String recordId) {
        DeleteRequest deleteRequest = new DeleteRequest("record_info", recordId);
        try {
            client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void scroll(){
        try {
            final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));

            SearchRequest searchRequest = new SearchRequest("record_info"); //index s
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            searchRequest.scroll(scroll);
            sourceBuilder.query(matchAllQuery());
            sourceBuilder.size(10); //设定每次返回多少条数据
            sourceBuilder.timeout(new TimeValue(1000));

            searchRequest.source(sourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            String scrollId = searchResponse.getScrollId();
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            while (searchHits != null && searchHits.length > 0) {
                System.out.println(scrollId);
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);

                scrollId = searchResponse.getScrollId();
                searchHits = searchResponse.getHits().getHits();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
