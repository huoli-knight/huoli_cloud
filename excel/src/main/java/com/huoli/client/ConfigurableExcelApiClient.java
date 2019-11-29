package com.huoli.client;

import com.huoli.api.ConfigurableExcelApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "producer")
public interface ConfigurableExcelApiClient extends ConfigurableExcelApi {
}
