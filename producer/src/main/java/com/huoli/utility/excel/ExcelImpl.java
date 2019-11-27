package com.huoli.utility.excel;

import com.huoli.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ExcelImpl implements Excel {

    @Override
    public List<User> readExcelFile(String fileName) {
        String serverPath = "";
        try {
            //得到 服务器 resources/static的绝对路径
            serverPath = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ").replace('/', '\\');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook workbook = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(serverPath + "\\" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //判断什么类型文件
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workbook == null) {
            return null;
        }
        int numOfSheet = workbook.getNumberOfSheets();
        List<User> result = new ArrayList<>();
        for (int i = 0; i < numOfSheet; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum == 0) {
                continue;
            }
            Row row = sheet.getRow(0);
            UserUtil userUtil;
            try {
                userUtil = isUserAttribute(row);
            } catch (RuntimeException re) {
                re.printStackTrace();
                log.warn("属性错误！");
                return null;
            }

            for (int j = 1; j <= lastRowNum; j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                short lastCellNum = row.getLastCellNum();
                List<String> userAttribute = new ArrayList<>();
                for (int k = 0; k <= lastCellNum; k++) {
                    if (row.getCell(k) == null) {
                        continue;
                    }
                    Cell cell = row.getCell(k);
                    cell.setCellType(CellType.STRING);
                    userAttribute.add(cell.getStringCellValue().trim());
                }
                try {
                    result.add(userUtil.getNewUser(userAttribute));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    log.warn("表的属性值错误！");
                }
            }
        }
        return result;
    }

    @Override
    public void createExcelFile(List<User> userList) {

    }

    private UserUtil isUserAttribute(Row row) throws RuntimeException {
        short lastCellNum = row.getLastCellNum();
        UserUtil userUtil = new UserUtil();
        for (int i = 0; i <= lastCellNum; i++) {
            if (row.getCell(i) == null) {
                continue;
            }
            if (!userUtil.isUserAttribute(row.getCell(i).getStringCellValue().trim())) {
                throw new RuntimeException();
            }
        }
        return userUtil;
    }


}
