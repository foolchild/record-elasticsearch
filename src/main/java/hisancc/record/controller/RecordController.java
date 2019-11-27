package hisancc.record.controller;

import hisancc.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * by zh
 * 2019/11/25
 **/
@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("")
    public void transform() {
        recordService.syncRecord();
    }

    @PostMapping("/{recordId}/sentence")
    public void sentence(@PathVariable String recordId, @RequestBody List<Map> list) {
        recordService.updateSentence(recordId, list);
    }

    @DeleteMapping("/{recordId}")
    public void delete(@PathVariable String recordId) {
        recordService.delete(recordId);
    }

    @GetMapping("")
    public void getScroll() {
        recordService.scroll();
    }
}
