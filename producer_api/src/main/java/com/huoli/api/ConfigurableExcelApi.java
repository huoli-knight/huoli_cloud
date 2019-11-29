package com.huoli.api;

import com.huoli.domain.ExcelContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/configurable")
public interface ConfigurableExcelApi {

    @PostMapping("/create")
    String createExcel(@RequestBody ExcelContext excelContext);
}
