package com.shaan.customoptimisticlocking.controller;

import com.shaan.customoptimisticlocking.model.dto.BookDto;
import com.shaan.customoptimisticlocking.service.BookService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookController {

  private final BookService bookService;

  @PutMapping("/update")
  public void update(@RequestBody BookDto bookDto) throws InterruptedException {
    // Update the entity
    bookService.update(bookDto);
  }

  @PutMapping("/update-test")
  public void updateTest(@RequestBody BookDto bookDto) {
    // Update the entity
    bookService.updateTest(bookDto);
  }

  @GetMapping("/books")
  public Iterable<BookDto> getBooks() {
    // Get all the books
    return bookService.getBooks();
  }

  @PostConstruct
  public void init() {
    // Initialize the database with some data
    bookService.init();
  }

}
