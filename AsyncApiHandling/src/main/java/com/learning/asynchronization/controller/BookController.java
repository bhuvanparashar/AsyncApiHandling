package com.learning.asynchronization.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.asynchronization.dto.Book;
import com.learning.asynchronization.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

	import java.util.List;
import java.util.concurrent.CompletableFuture;

	@RestController
	@RequestMapping("/api/books")
	public class BookController {

	    private final BookService bookService;

	    @Autowired
	    public BookController(BookService bookService) {
	        this.bookService = bookService;
	    }

	    @GetMapping
//	    public List<Book> getAllBooks() {
//	        return bookService.findAll();
	    
	    public CompletableFuture<ResponseEntity<List<Book>>> getAllBooks() {
	        return bookService.findAll().thenApply(ResponseEntity::ok);
	    
	    }

	    @GetMapping("/{id}")
//	    public ResponseEntity<Book> getBookById(@PathVariable int id) {
//	        Book book = bookService.findById(id);
//	        if (book == null) {
//	            return ResponseEntity.notFound().build();
//	        }
//	        return ResponseEntity.ok(book);
	    public CompletableFuture<ResponseEntity<Book>> getBookById(@PathVariable int id) {
	        return bookService.findById(id)
	                .thenApply(book -> book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build());
	    }

	    @PostMapping
//	    public Book createBook(@RequestBody Book book) {
//	        return bookService.save(book);
	    public CompletableFuture<ResponseEntity<Book>> createBook(@RequestBody Book book) {
	    	   return bookService.save(book)
	    	            .thenApply(savedBook -> new ResponseEntity<>(savedBook, HttpStatus.CREATED));
	    	
	    }

	    @PutMapping("/{id}")
//	    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
//	        Book book = bookService.findById(id);
//	        if (book == null) {	
//	            return ResponseEntity.notFound().build();
//	        }
//	        book.setTitle(bookDetails.getTitle());
//	        book.setAuthor(bookDetails.getAuthor());
//	        Book updatedBook = bookService.save(book);
//	        return ResponseEntity.ok(updatedBook);
	    public CompletableFuture<ResponseEntity<Book>> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
	        return bookService.findById(id)
	                .thenCompose(existingBook -> {
	                    if (existingBook == null) {
	                        return CompletableFuture.completedFuture(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));
	                    }
	                    updatedBook.setId(id);
	                    return bookService.save(updatedBook)
	                            .thenApply(savedBook -> new ResponseEntity<>(savedBook, HttpStatus.OK));
	                });
    }

	    @DeleteMapping("/{id}")
//	    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
//	        Book book = bookService.findById(id);
//	        if (book == null) {
//	            return ResponseEntity.notFound().build();
//	        }
//	        bookService.deleteById(id);
//	        return ResponseEntity.noContent().build();
	    
	    public CompletableFuture<ResponseEntity<Void>> deleteBook(@PathVariable int id) {
	        return bookService.findById(id)
	                .thenCompose(existingBook -> {
	                    if (existingBook == null) {
	                        return CompletableFuture.completedFuture(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	                    }
	                    return bookService.deleteById(id)
	                            .thenApply(v -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT));
	                });
	    }
}
