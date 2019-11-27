package hisancc.record.vo;

import lombok.Data;

/**
 * by zh
 * 2019/11/26
 **/
@Data
public class SentenceVO {
    private Long silenceDuration;
    private Long sentenceId;
    private Long endTime;
    private Long speechRate;
    private Long beginTime;
    private String sentenceText;
    private Long channelId;
    private Long emotionValue;
}
