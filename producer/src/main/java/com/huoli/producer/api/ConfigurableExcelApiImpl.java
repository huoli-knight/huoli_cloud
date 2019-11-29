package com.huoli.producer.api;

import com.huoli.api.ConfigurableExcelApi;
import com.huoli.domain.ExcelContext;
import com.huoli.utility.excel.ConfigurableExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configurable")
public class ConfigurableExcelApiImpl implements ConfigurableExcelApi {

    @Autowired
    private ConfigurableExcel configurableExcel;

    @Override
    @PostMapping("/create")
    public String createExcel(@RequestBody ExcelContext excelContext) {
        return configurableExcel.createExcel(excelContext);
    }
}
