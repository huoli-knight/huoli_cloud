package com.huoli.controller;

import com.huoli.client.ConfigurableExcelApiClient;
import com.huoli.domain.ExcelContext;
import com.huoli.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ConfigurableExcelApiClient configurableExcelApiClient;

    @PostMapping("/add")
    @CrossOrigin
    public Result addData(ExcelContext excelContext) {
        Result result = new Result();
        result.setMessage("创建文件目录：" + configurableExcelApiClient.createExcel(excelContext));
        result.setCode(1);
        return result;
    }
}
