package org.pragma.foodcourtmanager.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@AllArgsConstructor
public class MessageRequest{

    private String phoneNumber;
    private String message;
}
