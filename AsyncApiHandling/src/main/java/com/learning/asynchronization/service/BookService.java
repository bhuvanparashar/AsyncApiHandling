package com.learning.asynchronization.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.learning.asynchronization.dto.Book;

@Service
public interface BookService {

	CompletableFuture<List<Book>> findAll();
	CompletableFuture <Book> findById(int id);
	CompletableFuture<Book> save(Book book);
	CompletableFuture<Void> deleteById(int id);
	}



