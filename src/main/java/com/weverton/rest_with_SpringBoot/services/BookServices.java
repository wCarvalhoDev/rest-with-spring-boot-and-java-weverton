package com.weverton.rest_with_SpringBoot.services;

import com.weverton.rest_with_SpringBoot.controllers.BookController;
import com.weverton.rest_with_SpringBoot.data.dto.BookDTO;
import com.weverton.rest_with_SpringBoot.exception.RequiredObjectIsNullException;
import com.weverton.rest_with_SpringBoot.exception.ResourceNotFoundException;
import com.weverton.rest_with_SpringBoot.models.Book;
import com.weverton.rest_with_SpringBoot.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.weverton.rest_with_SpringBoot.mapper.ObjectMapper.parseListObjects;
import static com.weverton.rest_with_SpringBoot.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll() {
        logger.info("find all books");

        var books = parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(this::addHateoasLinks);
        return books;
    }

    public BookDTO findById(Long id) {
        logger.info("find Book with ID {}", id);
        var entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating new book");

        var entity = parseObject(book, Book.class);
        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating book");

        Book entity = repository.findById(book.getId()).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(new Date());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting book with ID {}", id);

        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        repository.delete(entity);
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
