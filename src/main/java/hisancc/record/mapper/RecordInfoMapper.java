package hisancc.record.mapper;

import hisancc.record.entity.DtRecordInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * by zh
 * 2019/11/26
 **/
@Mapper
public interface RecordInfoMapper extends BaseMapper<DtRecordInfo> {
    Long count();
}
