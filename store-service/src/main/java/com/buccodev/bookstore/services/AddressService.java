package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Address;
import com.buccodev.bookstore.entity.dto.AddressDTO;
import com.buccodev.bookstore.repositories.AddressRepository;
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
public class AddressService {

    @Autowired
    private  AddressRepository repository;


    public UUID saveAddress(AddressDTO addressDTO){

       try{

           Address address = AddressDTO.toAddressFroDTO(addressDTO);

           return repository.save(address).getId();

       } catch (DataIntegrityViolationException | ConstraintViolationException e){

           throw new DataBaseException(e.getMessage());

       }
       
    }

    public AddressDTO findAddressById(UUID id){

            Address address = repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id));

            return AddressDTO.fromAddress(address);

    }

    public List<Address> findAllAddress(){

        return repository.findAll();
    }

    public void deleteAddressById(UUID id){

        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e){

            throw new ResourceNotFoundException(id);

        }  catch (DataIntegrityViolationException e){

            throw new DataBaseException(e.getMessage());
        }

    }

    public void updateAddress(UUID id, AddressDTO newAddress){

          Address address = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

          updateData(address, newAddress);

          repository.save(address);


    }

    private void updateData(Address oldAddress, AddressDTO newAddress){
        oldAddress.setCity(newAddress.city());
        oldAddress.setComplement(newAddress.complement());
        oldAddress.setNumber(newAddress.number());
        oldAddress.setNeighborhood(newAddress.neighborhood());
        oldAddress.setStreet(newAddress.street());
    }

}
