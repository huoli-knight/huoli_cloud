package com.huoli.utility.excel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Component
@Data
@Slf4j
public class CurrentUtility {

    private String pathSuffer;

    public CurrentUtility() {
        try {
            pathSuffer = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ").replace('/', '\\');
            pathSuffer += "\\";
        } catch (FileNotFoundException e) {
            log.warn("服务器路径读取错误！", e);
        }
    }
}
