package com.huoli.producer.api;

import com.huoli.utility.excel.CurrentUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@Slf4j
@RequestMapping(value = "/file")
public class ExelFileImpl {

    @Autowired
    private CurrentUtility currentUtility;

    @PostMapping("/upload")
    public void fileUpload(@RequestParam(value = "fileUpload")MultipartFile fileUpload) {
        String ResultPath = null;
        if ((ResultPath = currentUtility.getPathSuffer()) == null) {
            return;
        }
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(ResultPath + "\\" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    @PostMapping("/download")
    @CrossOrigin
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        String ResultPath = null;
        if ((ResultPath = currentUtility.getPathSuffer()) == null) {
            return;
        }
        response.setContentType("application/vnd.ms-excel;charset=UTF-8;");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            log.warn("500,返回出错!", e);
        }
        byte[] buffer = new byte[1024];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(ResultPath + fileName);
        } catch (FileNotFoundException e) {
            log.warn("打开文件出错!", e);
        }
        try {
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException ie) {
            log.warn("读取错误", ie);
        }
    }

}
