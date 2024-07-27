package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Address;
import com.buccodev.bookstore.entity.Client;

public record AddressDTO(String city, String neighborhood, String street, String number, String complement, Client client) {


    public static AddressDTO fromAddress(Address address){
        return new AddressDTO(
                address.getCity(),
                address.getNeighborhood(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getClient()
        );
    }

    public static Address toAddressFroDTO(AddressDTO addressDTO){
        return new Address(
                null,
                addressDTO.city(),
                addressDTO.neighborhood(),
                addressDTO.street(),
                addressDTO.number(),
                addressDTO.complement(),
                addressDTO.client()
        );
    }

}
