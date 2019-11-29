package com.huoli.utility.excel;

import com.huoli.domain.ExcelContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
@Slf4j
public class ConfigurableExcelImpl implements ConfigurableExcel {

    @Override
    public String createExcel(ExcelContext excelContext) {
        String ResultPath = null;
        try {
            //得到 服务器 resources/static的绝对路径,这里是服务器部署的路径，是构建后的路径
            ResultPath = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ").replace('/', '\\');
        } catch (FileNotFoundException e) {
            log.warn("服务器路径读取错误！", e);
            return null;
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(excelContext.getSheetName());
        createRow(sheet, excelContext);
        File file = null;
        ResultPath = createNewFile(file, ResultPath + "\\" + excelContext.getFileName());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(ResultPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(out);
            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultPath;
    }

    private String createNewFile(File file, String filePath) {
        file =  new File(filePath + ".xls");
        if (!file.exists()) {
            try {
                file.createNewFile();
                return filePath + ".xls";
            } catch (IOException e) {
                log.warn("创建文件错误！", e);
            }
        }
        return createNewFile(file, filePath, 0);
    }

    private String createNewFile(File file, String filePath, int suffix) {
        file =  new File(filePath + suffix + ".xls");
        if (!file.exists()) {
            try {
                file.createNewFile();
                return filePath + suffix + ".xls";
            } catch (IOException e) {
                log.warn("创建文件错误！", e);
            }
        }
        return createNewFile(file, filePath, suffix++);
    }

    private void createCell(HSSFRow row, HSSFCell cell, String[] state) {
        for (int i = 0; i < state.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(state[i]);
        }
    }

    private void createRow(HSSFSheet sheet, ExcelContext excelContext) {
        for (int i = 0; i < excelContext.getAttribute().size(); i++) {
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = null;
            String[] state = excelContext.getAttribute().get(0).getContext();
            createCell(row, cell, state);
        }
    }
}

