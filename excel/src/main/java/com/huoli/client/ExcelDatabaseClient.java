package com.huoli.client;

import com.huoli.api.ExcelDatabase;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "producer")
public interface ExcelDatabaseClient extends ExcelDatabase {
}
