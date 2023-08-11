package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.UserResponse;

import java.util.List;

public interface IUserHandler{
    UserResponse getUser(String documentId);

}
