package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.MessageRequest;
import org.pragma.foodcourtmanager.application.dto.request.TraceabilityRequest;
import org.pragma.foodcourtmanager.application.dto.response.TraceabilityResponse;
import org.pragma.foodcourtmanager.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "TRACEABILITY-API-FOOD-COURT" , url = "http://localhost:8093" , configuration = FeignConfig.class)
public interface ITraceabilityFeignClient{

    @PostMapping(value = "/traceability/", consumes = MediaType.APPLICATION_JSON_VALUE)
    String saveTraceability(@RequestBody TraceabilityRequest traceabilityRequest);

    @GetMapping(value = "/traceability/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<TraceabilityResponse> getTraceability(@PathVariable(name = "customerId") Long customerId);

    @GetMapping(value = "/traceability/", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<TraceabilityResponse> getAllTraceability();


}
