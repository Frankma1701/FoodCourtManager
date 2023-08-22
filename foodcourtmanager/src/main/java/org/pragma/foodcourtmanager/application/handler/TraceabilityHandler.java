package org.pragma.foodcourtmanager.application.handler;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.MessageRequest;
import org.pragma.foodcourtmanager.application.dto.request.TraceabilityRequest;
import org.pragma.foodcourtmanager.application.dto.response.TraceabilityResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceabilityHandler{

    private final ITraceabilityFeignClient iTraceabilityFeignClient;

    public void saveTraceability (String token ,TraceabilityRequest traceabilityRequest){
        String tokenFormat = "Bearer " + token;

        iTraceabilityFeignClient.saveTraceability(tokenFormat,traceabilityRequest);
    }

    public List<TraceabilityResponse> getTraceability (String token,Long customerId){
        String tokenFormat = "Bearer " + token;
        return iTraceabilityFeignClient.getTraceability(tokenFormat, customerId);
    }
    public List<TraceabilityResponse> getAllTraceability (String token){
        String tokenFormat = "Bearer " + token;

        return iTraceabilityFeignClient.getAllTraceability(tokenFormat);
    }

}
