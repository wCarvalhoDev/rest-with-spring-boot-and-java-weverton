package com.weverton.rest_with_SpringBoot.controllers;

import com.weverton.rest_with_SpringBoot.controllers.docs.BookControllerDocs;
import com.weverton.rest_with_SpringBoot.data.dto.BookDTO;
import com.weverton.rest_with_SpringBoot.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Books", description = "Endpoint for managing books")
public class BookController implements BookControllerDocs {

    @Autowired
    private BookServices services;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            })
    @Override
    public List<BookDTO> findAll() {
        return services.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            })
    @Override
    public BookDTO findById(@PathVariable("id") Long id) {
        return services.findById(id);
    }

    @PostMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            })
    @Override
    public BookDTO create(@RequestBody BookDTO dto) {
        return services.create(dto);
    }

    @PutMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE},
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            })
    @Override
    public BookDTO update(@RequestBody BookDTO dto) {
        return services.update(dto);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}
