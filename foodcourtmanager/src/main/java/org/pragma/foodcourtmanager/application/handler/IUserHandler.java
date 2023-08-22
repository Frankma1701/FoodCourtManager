package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface IUserHandler{

      UserResponse getUserFromApi (String documentId);

      UserResponse getUser (Long id);


}
