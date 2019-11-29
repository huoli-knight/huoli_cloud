package com.huoli.domain;

import lombok.Data;

import java.util.List;

@Data
public class ExcelContext {

    private String sheetName;

    private String fileName;

    private List<Context> attribute;

}
