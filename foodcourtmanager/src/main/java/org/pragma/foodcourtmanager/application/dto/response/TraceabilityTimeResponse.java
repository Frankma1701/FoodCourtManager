package org.pragma.foodcourtmanager.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
public class TraceabilityTimeResponse{

    private Long orderId;
    private Long customerId;
    private String customerEmail;
    private String startTime;
    private String finishTime;
    private Long employeeId;
    private String employeeEmail;
    private String totalOrderTime;
}


