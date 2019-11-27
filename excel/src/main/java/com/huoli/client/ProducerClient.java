package com.huoli.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "producer")
public interface ProducerClient {

    @GetMapping(value = "/excel/hello")
    String helloWorld();
}
