package com.weverton.rest_with_SpringBoot.repository;

import com.weverton.rest_with_SpringBoot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
