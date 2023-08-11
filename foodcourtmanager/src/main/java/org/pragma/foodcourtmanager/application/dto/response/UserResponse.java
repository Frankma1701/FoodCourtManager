package org.pragma.foodcourtmanager.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UserResponse{
    private Long id;
    private String name;
    private String lastName;
    private String documentId;
    private String cellPhoneNumber;
    private LocalDate birthDate;
    private String email;
    private Long roleId;
}
