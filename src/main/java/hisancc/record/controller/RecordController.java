package hisancc.record.controller;

import hisancc.record.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * by zh
 * 2019/11/25
 **/
@Api(tags = "elasticsearch 基本操作")
@RestController
@RequestMapping("/api")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @ApiOperation(value = "同步record info到elasticsearch")
    @PostMapping("")
    public void transform() {
        recordService.syncRecord();
    }

    @ApiOperation(value = "修改record句子")
    @PostMapping("/{recordId}/sentence")
    public void sentence(@PathVariable String recordId, @RequestBody List<Map> list) {
        recordService.updateSentence(recordId, list);
    }

    @ApiOperation(value = "删除record记录")
    @DeleteMapping("/{recordId}")
    public void delete(@PathVariable String recordId) {
        recordService.delete(recordId);
    }

    @ApiOperation(value = "scroll elasticsearch 记录")
    @GetMapping("/")
    public void getScroll() {
        recordService.scroll();
    }

    @ApiOperation(value = "elasticsearch bool 查询")
    @GetMapping("/bool")
    public void bool() {
        recordService.bool();
    }

    @ApiOperation(value = "计数")
    @GetMapping("/count")
    public void count() {
        recordService.count();
    }
}
