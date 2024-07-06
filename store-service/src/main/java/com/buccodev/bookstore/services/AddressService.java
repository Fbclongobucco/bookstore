package com.buccodev.bookstore.services;

import com.buccodev.bookstore.entity.Address;
import com.buccodev.bookstore.repositories.AddressRepository;
import com.buccodev.bookstore.services.exceptions.DataBaseException;
import com.buccodev.bookstore.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AddressService {

    @Autowired
    private  AddressRepository repository;


    public UUID saveAddress(Address address){

       try{
           UUID uuid = repository.save(address).getId();
           
           return uuid;

       } catch (DataIntegrityViolationException | ConstraintViolationException e){

           throw new DataBaseException(e.getMessage());

       }
       
    }

    public Address findAddressById(UUID id){

            Optional<Address> address = repository.findById(id);

            return address.orElseThrow(()-> new ResourceNotFoundException(id));

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

    public Address updateAddress(UUID id, Address newAddress){

      try{

          Address address = repository.findById(id).get();

          updateData(address, newAddress);

         return repository.save(address);

      }  catch (EntityNotFoundException e){

          throw new ResourceNotFoundException(id);

      }
    }

    private void updateData(Address oldAddress, Address newAddress){
        oldAddress.setCity(newAddress.getCity());
        oldAddress.setComplement(newAddress.getComplement());
        oldAddress.setNumber(newAddress.getNumber());
        oldAddress.setNeighborhood(newAddress.getNeighborhood());
        oldAddress.setStreet(newAddress.getStreet());
    }

}
