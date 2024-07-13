package com.buccodev.bookstore.resourses;

import com.buccodev.bookstore.entity.Publisher;
import com.buccodev.bookstore.entity.dto.PublisherDTO;
import com.buccodev.bookstore.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/publisher")
public class PublisherResource {

    @Autowired
    private PublisherService service;

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublisher() {

        var publishers = service.findAllPublisher();

        var publisherDTO = publishers.stream().map(PublisherDTO::fromPublisher).toList();

        return ResponseEntity.ok().body(publisherDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable UUID id) {

        var publisher = service.findPublisherById(id);

        var publisherDTO = PublisherDTO.fromPublisher(publisher);

        return ResponseEntity.ok().body(publisherDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PublisherDTO> findPublisherByName(@PathVariable String name) {

        var publisher = service.findByName(name);

        var publisherDTO = PublisherDTO.fromPublisher(publisher);

        return ResponseEntity.ok().body(publisherDTO);

    }

    @PostMapping
    public ResponseEntity<Void> savePublisher(@RequestBody PublisherDTO publisherDTO) {

        var publisher = PublisherDTO.toPublisherFromDTO(publisherDTO);

        service.savePublisher(publisher);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable UUID id) {

        service.deletePublisherById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePublisher(@PathVariable UUID id, @RequestBody PublisherDTO publisherDTO) {


        service.updatePublisher(id, PublisherDTO.toPublisherFromDTO(publisherDTO));

        return ResponseEntity.noContent().build();

    }


}


