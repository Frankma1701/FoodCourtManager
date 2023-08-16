package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.response.UserResponse;
import org.pragma.foodcourtmanager.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-API-FOOD-COURT" , url = "http://localhost:8090" , configuration = FeignConfig.class)
public interface IUserFeignClient{

    @GetMapping(value = "/user/{documentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserResponse getUser(@PathVariable("documentId") String documentId);

    @GetMapping(value = "/user/find/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserResponse getUserById(@PathVariable("id") Long id);
}
