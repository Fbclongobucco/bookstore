package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Address;
import com.buccodev.bookstore.entity.Client;
import com.buccodev.bookstore.entity.dto.ClientDTO;
import com.buccodev.bookstore.repositories.ClientRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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


    public UUID saveClient(ClientDTO clientDTO){

        try{

            Client client = ClientDTO.toClientFromDTO(clientDTO);

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

    public Client updateClient(UUID id, Client newClient){

        try{


            Client client = repository.findById(id).get();

            updateData(client, newClient);

            return repository.save(client);

        }  catch (EntityNotFoundException e){

            throw new ResourceNotFoundException(id);

        }
    }

    private void updateData(Client oldClient, Client newClient) {

        oldClient.setCpf(newClient.getCpf());
        oldClient.setEmail(newClient.getEmail());
        oldClient.setName(newClient.getName());
        oldClient.setPassword(newClient.getPassword());
    }

}
