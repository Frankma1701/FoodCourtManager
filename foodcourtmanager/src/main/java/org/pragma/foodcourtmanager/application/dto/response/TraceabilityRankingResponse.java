package org.pragma.foodcourtmanager.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
public class TraceabilityRankingResponse{
    private String ranking;
    private Long employeeId;
    private String employeeFullName;
    private String documentId;
    private String averageTime;
}


