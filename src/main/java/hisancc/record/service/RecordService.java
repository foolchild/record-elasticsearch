package hisancc.record.service;

import hisancc.record.vo.SentenceVO;

import java.util.List;
import java.util.Map;

/**
 * by zh
 * 2019/11/25
 **/
public interface RecordService {

    void syncRecord();

    void updateSentence(String recordId, List<Map> list);

    void delete(String recordId);

    void scroll();
}
