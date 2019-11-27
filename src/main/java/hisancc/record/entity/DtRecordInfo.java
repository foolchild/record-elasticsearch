package hisancc.record.entity;

import lombok.Data;

/**
 * by zh
 * 2019/11/26
 **/
@Data
public class DtRecordInfo {
    private String recordId;

    private String recordFile;

    private String recordTime;

    private Long recordDuration;

    private String recordType;

    private String ucid;

    private String contactId;

    private String deviceDn;

    private Long deviceType;

    private Long callType;

    private Long callDirection;

    private String oriAni;

    private String oriDnis;

    private String callingParty;

    private String calledParty;

    private String thirdParty;

    private String otherParty;

    private Long callId;

    private Long oldCallId;

    private Long secOldCallId;

    private String loginId;

    private String agentId;

    private String agentName;

    private String tenantId;

    private String platformId;

    private String batchId;

    private String localIp;

    private String localParty;

    private String remoteIp;

    private String remoteParty;

    private String recordService;

    private String rtpDirection;

    private String oriRecordFile;

    private String relateField1;

    private String relateField2;

    private String relateField3;

    private String relateField4;

    private String relateField5;

    private String relateField6;

    private String relateField7;

    private String relateField8;

    private String relateField9;

    private String createTime;

}
