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

    public void saveTraceability (TraceabilityRequest traceabilityRequest){
        iTraceabilityFeignClient.saveTraceability(traceabilityRequest);
    }

    public List<TraceabilityResponse> getTraceability (Long customerId){
        return iTraceabilityFeignClient.getTraceability(customerId);
    }
    public List<TraceabilityResponse> getAllTraceability (){
        return iTraceabilityFeignClient.getAllTraceability();
    }

}
