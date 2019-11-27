package com.huoli.api;

import com.huoli.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/excel")
public interface ExcelDatabase {

    @GetMapping(value = "/select")
    List<User>  getExcelDatabase();
}
