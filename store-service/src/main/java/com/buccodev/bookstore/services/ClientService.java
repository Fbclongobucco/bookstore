package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Address;
import com.buccodev.bookstore.entity.Client;
import com.buccodev.bookstore.entity.dto.AddressDTO;
import com.buccodev.bookstore.entity.dto.ClientDTO;
import com.buccodev.bookstore.repositories.AddressRepository;
import com.buccodev.bookstore.repositories.ClientRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;


    public UUID saveClient(ClientDTO clientDTO){

        try{

            Client client = ClientDTO.toClientFromDTO(clientDTO);

            List<AddressDTO> addressDTOS = clientDTO.addresses().stream().toList();

            List<Address> addresses = addressDTOS.stream().map(AddressDTO::toAddressFroDTO).toList();

            client.getAddress().addAll(addresses);

            addressRepository.saveAll(addresses);

            return repository.save(client).getId();

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseException(e.getMessage());

        }
    }

    public Client findClientById(UUID id){

        Optional<Client> book = repository.findById(id);

        return book.orElseThrow(()-> new ResourceNotFoundException(id));

    }

    public List<ClientDTO> findAllClient(){

        List<Client> clients = repository.findAll();

        return clients.stream().map(ClientDTO::fromClient).toList();
    }

    public void deleteClientById(UUID id){

        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e){

            throw new ResourceNotFoundException(id);

        }  catch (DataIntegrityViolationException e){

            throw new DataBaseException(e.getMessage());
        }

    }

    public void updateClient(UUID id, ClientDTO clientDTO){


            Client client = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

            updateData(client, clientDTO);

            repository.save(client);

    }

    public void addAddress(UUID idClient, AddressDTO addressDTO){

        Address address = AddressDTO.toAddressFroDTO(addressDTO);

        Client client = repository.findById(idClient).orElseThrow(()-> new ResourceNotFoundException(idClient));

        client.getAddress().add(address);

        address.setClient(client);

        addressRepository.save(address);

        repository.save(client);

    }

    public void removeAddress(UUID idClient, UUID idAddress ){


        Client client = repository.findById(idClient).orElseThrow(()-> new ResourceNotFoundException(idClient));


        client.getAddress().removeIf(address -> address.getId().equals(idAddress));


        addressRepository.deleteById(idAddress);

        repository.save(client);


    }

    private void updateData(Client oldClient, ClientDTO newClient) {

        oldClient.setCpf(newClient.cpf());
        oldClient.setEmail(newClient.email());
        oldClient.setName(newClient.name());
        oldClient.setPassword(newClient.password());
    }

}
