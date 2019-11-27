package hisancc.record.factory;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

/**
 * by zh
 * 2019/11/26
 **/
@Component
public class RestHighLevelClientSingletonFactory {

    public final static String HOST = "172.16.1.205";
    // http请求的端口是9200，客户端是9300
    public final static int PORT = 9200;
    private static RestHighLevelClient client;

    public static RestHighLevelClient getInstance() {
        if(client == null){
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(HOST, PORT, "http")));
        }
        return client;
    }

}
