package com.learning.asynchronization.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.asynchronization.dto.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>  {


}
