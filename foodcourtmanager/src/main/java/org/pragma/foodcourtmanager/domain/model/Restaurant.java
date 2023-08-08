package org.pragma.foodcourtmanager.domain.model;

public class Restaurant{

    private Long id;
    private String name;
    private String address;
    private Long ownerId;
    private String phoneNumber;
    private String logoUrl;
    private String nit;

    public Restaurant (Long id, String name, String address, Long ownerId, String phoneNumber, String logoUrl, String nit){
        this.id = id;
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.phoneNumber = phoneNumber;
        this.logoUrl = logoUrl;
        this.nit = nit;
    }

    public Long getId (){
        return id;
    }

    public void setId (Long id){
        this.id = id;
    }

    public String getName (){
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getAddress (){
        return address;
    }

    public void setAddress (String address){
        this.address = address;
    }

    public Long getOwnerId (){
        return ownerId;
    }

    public void setOwnerId (Long ownerId){
        this.ownerId = ownerId;
    }

    public String getPhoneNumber (){
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getLogoUrl (){
        return logoUrl;
    }

    public void setLogoUrl (String logoUrl){
        this.logoUrl = logoUrl;
    }

    public String getNit (){
        return nit;
    }

    public void setNit (String nit){
        this.nit = nit;
    }
}
