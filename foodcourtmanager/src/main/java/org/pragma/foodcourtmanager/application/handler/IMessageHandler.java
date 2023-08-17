package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.MessageRequest;
import org.pragma.foodcourtmanager.application.dto.response.UserResponse;

public interface IMessageHandler{
    public String sendMessage(MessageRequest messageRequest);

}
