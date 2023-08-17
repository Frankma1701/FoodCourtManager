package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.MessageRequest;
import org.pragma.foodcourtmanager.application.dto.response.UserResponse;
import org.pragma.foodcourtmanager.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MESSAGE-API-FOOD-COURT" , url = "http://localhost:8092" , configuration = FeignConfig.class)
public interface IMessageFeignClient{

    @GetMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    String sendMessage(@RequestBody MessageRequest messageRequest);


}
