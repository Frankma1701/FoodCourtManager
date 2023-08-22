package org.pragma.foodcourtmanager.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
public class TraceabilityResponse{

    private Long orderId;
    private Long customerId;
    private String customerEmail;
    private LocalDateTime date;
    private String previousStatus;
    private String newStatus;
    private Long employeeId;
    private String employeeEmail;
    private Long restaurantId;
}
