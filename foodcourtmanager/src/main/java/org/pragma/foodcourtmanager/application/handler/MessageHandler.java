package org.pragma.foodcourtmanager.application.handler;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.MessageRequest;
import org.pragma.foodcourtmanager.application.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageHandler{

    private final IMessageFeignClient iMessageFeignClient;

    public String sendMessage (String token, MessageRequest messageRequest){
        String tokenFormat = "Bearer " + token;
        return iMessageFeignClient.sendMessage(tokenFormat, messageRequest);
    }

}
