package com.weverton.rest_with_SpringBoot.services;

import com.weverton.rest_with_SpringBoot.data.dto.PersonDTO;
import com.weverton.rest_with_SpringBoot.exception.ResourceNotFoundException;
import static com.weverton.rest_with_SpringBoot.mapper.ObjectMapper.parseListObjects;
import static com.weverton.rest_with_SpringBoot.mapper.ObjectMapper.parseObject;

import com.weverton.rest_with_SpringBoot.models.Person;
import com.weverton.rest_with_SpringBoot.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all People!");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public  PersonDTO findById(Long id) {
        logger.info("Finding one Person.");
        var entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        return parseObject(entity, PersonDTO.class);
    }

    public  PersonDTO create( PersonDTO person) {
        logger.info("Creating one person.");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public  PersonDTO update( PersonDTO person) {
        logger.info("Updating one Person.");
         Person entity = repository.findById(person.getId()).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person.");
        Person entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        repository.delete(entity);
    }
}