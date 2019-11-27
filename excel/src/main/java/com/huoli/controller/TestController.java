package com.huoli.controller;

import com.huoli.client.ExcelDatabaseClient;
import com.huoli.client.ExcelFileClient;
import com.huoli.client.ProducerClient;
import com.huoli.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProducerClient producerClient;

    @Autowired
    private ExcelFileClient excelControllerClient;

    @Autowired
    private ExcelDatabaseClient excelDatabaseClient;

    @GetMapping("/user")
    @CrossOrigin
    public List<User> getAllUser(){
        return excelDatabaseClient.getExcelDatabase();
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return excelControllerClient.helloWorld();
//        return producerClient.helloWorld();
    }

    @GetMapping("/read")
    @CrossOrigin
    public List<User> readExcel(@RequestParam("path") String path) {
        return excelControllerClient.testData(path);
    }

}
