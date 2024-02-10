package com.learning.asynchronization.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learning.asynchronization.dao.BookRepository;
import com.learning.asynchronization.dto.Book;

@Service
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Async
    public CompletableFuture<List<Book>> findAll() {
        return CompletableFuture.completedFuture(bookRepository.findAll());
    }


    @Override
    @Async
    public CompletableFuture<Book> save(Book book) {
    	return CompletableFuture.completedFuture(bookRepository.save(book));
    }

	@Override
	@Async
	public CompletableFuture<Book> findById(int id) {
		
		return CompletableFuture.completedFuture(bookRepository.findById(id).orElse(null));
	}

	@Override
	@Async
	public CompletableFuture<Void> deleteById(int id) {
		bookRepository.deleteById(id);
		return CompletableFuture.completedFuture(null);
	}

}
