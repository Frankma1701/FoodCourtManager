package org.pragma.foodcourtmanager.application.handler;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandler{

    private final IUserFeignClient iUserFeignClient;

    public UserResponse getUserFromApi (String documentId){
        return iUserFeignClient.getUser(documentId);
    }

    public UserResponse getUser (Long id){
        return iUserFeignClient.getUserById(id);
    }
}
