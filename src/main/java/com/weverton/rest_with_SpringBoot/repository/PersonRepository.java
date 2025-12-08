package com.weverton.rest_with_SpringBoot.repository;

import com.weverton.rest_with_SpringBoot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
