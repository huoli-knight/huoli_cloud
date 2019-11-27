package com.huoli.producer.api;

import com.huoli.api.ExcelDatabase;
import com.huoli.domain.User;
import com.huoli.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/excel")
@RestController
public class ExcelDatabaseImpl implements ExcelDatabase {

    @Autowired
    private ExcelService excelService;

    @Override
    @GetMapping("/select")
    public List<User> getExcelDatabase() {
        return excelService.getExcelDatabase();
    }
}
