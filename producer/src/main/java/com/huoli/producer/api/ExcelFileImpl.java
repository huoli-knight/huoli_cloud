package com.huoli.producer.api;

import com.huoli.api.ExcelFile;
import com.huoli.domain.User;
import com.huoli.utility.excel.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelFileImpl implements ExcelFile {

    @Autowired
    private Excel excel;

    @GetMapping("/test")
    @Override
    public List<User> testData(@RequestParam("path") String path) {
        List<User> userList = excel.readExcelFile(path);
        return userList;
    }

    @GetMapping("/hello")
    @Override
    public String helloWorld(){
        return "helloWorld!!!";
    }

}
