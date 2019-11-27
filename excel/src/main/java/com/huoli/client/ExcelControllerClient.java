package com.huoli.client;

import com.huoli.api.ExcelController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "producer")
public interface ExcelControllerClient extends ExcelController {
}
