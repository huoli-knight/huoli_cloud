package com.huoli.client;

import com.huoli.api.ExcelFile;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "producer")
public interface ExcelFileClient extends ExcelFile {
}
