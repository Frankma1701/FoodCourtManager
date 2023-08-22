package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.MessageRequest;
import org.pragma.foodcourtmanager.application.dto.request.TraceabilityRequest;
import org.pragma.foodcourtmanager.application.dto.response.TraceabilityResponse;

import java.util.List;

public interface ITraceabilityHandler{
     void saveTraceability(TraceabilityRequest traceabilityRequest);

     List<TraceabilityResponse> getTraceability(Long orderId);

     List<TraceabilityResponse> getAllTraceability();

}
