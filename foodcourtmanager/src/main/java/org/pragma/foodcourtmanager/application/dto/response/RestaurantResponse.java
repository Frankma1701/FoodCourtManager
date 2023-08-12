package org.pragma.foodcourtmanager.application.dto.response;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RestaurantResponse{
    private Long id;
    private String name;
    private String address;
    private Long ownerId;
    private String phoneNumber;
    private String logoUrl;
    private String nit;

}
