package com.huoli.api;

import com.huoli.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/excel")
public interface ExcelController {

    @GetMapping(value = "/test")
    List<User> testData(@RequestParam("path") String path);

    @GetMapping(value = "/hello")
    String helloWorld();

}
