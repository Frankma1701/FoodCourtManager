package org.pragma.foodcourtmanager.infrastructure.input.rest;


import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.handler.RestaurantHandler;
import org.pragma.foodcourtmanager.application.handler.UserHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController{

    private final UserHandler userHandler;


}
