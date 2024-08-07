package com.buccodev.bookstore.entity.dto;

import com.buccodev.bookstore.entity.Address;
import com.buccodev.bookstore.entity.Client;
import com.buccodev.bookstore.entity.Order;

import java.util.List;
import java.util.Set;

public record ClientDTO(String name, String email, String cpf, String password, List<AddressDTO> addresses, List<Order> orders) {

        public static ClientDTO fromClient(Client client){

            return new ClientDTO(
                    client.getName(),
                    client.getEmail(),
                    client.getCpf(),
                    client.getPassword(),
                    client.getAddress().stream().map(AddressDTO::fromAddress).toList(),
                    client.getOrders()
            );

        }

        public static Client toClientFromDTO(ClientDTO clientDTO){
            return new Client(
                null,
                    clientDTO.name(),
                    clientDTO.email(),
                    clientDTO.cpf(),
                    clientDTO.password()
            );
        }


}
