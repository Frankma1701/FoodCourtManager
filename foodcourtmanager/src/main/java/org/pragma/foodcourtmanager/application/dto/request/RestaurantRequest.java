package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RestaurantRequest{

    private String name;
    private String address;
    private String documentId;
    private String phoneNumber;
    private String logoUrl;
    private String nit;



}
