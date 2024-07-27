package com.buccodev.bookstore.resourses;

import com.buccodev.bookstore.entity.Client;
import com.buccodev.bookstore.entity.dto.AddressDTO;
import com.buccodev.bookstore.entity.dto.ClientDTO;
import com.buccodev.bookstore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClient(){

        List<ClientDTO> listDTO = service.findAllClient();

        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientByID(@PathVariable UUID id){

        Client client = service.findClientById(id);

        ClientDTO clientDTO = ClientDTO.fromClient(client);

        return ResponseEntity.ok().body(clientDTO);

    }

    @PostMapping
    public ResponseEntity<Void> saveClient(@RequestBody ClientDTO clientDTO){

        service.saveClient(clientDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable UUID id, @RequestBody ClientDTO clientDTO){



        service.updateClient(id, clientDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable UUID id){

        service.deleteClientById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<Void> addAddress(@PathVariable UUID id, @RequestBody AddressDTO addressDTO){

        service.addAddress(id, addressDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{idClient}/address/{idAddress}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID idClient, @PathVariable UUID idAddress ){

        service.removeAddress(idClient, idAddress);

        return ResponseEntity.ok().build();

    }

}
