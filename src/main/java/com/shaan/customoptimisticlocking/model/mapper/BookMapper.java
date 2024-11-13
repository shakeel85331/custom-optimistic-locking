package com.shaan.customoptimisticlocking.model.mapper;

import com.shaan.customoptimisticlocking.model.dto.BookDto;
import com.shaan.customoptimisticlocking.model.entity.Book;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
  public static BookDto toDto(Book book) {
    BookDto bookDto = new BookDto();
    bookDto.setId(book.getId());
    bookDto.setName(book.getName());
    bookDto.setAuthor(book.getAuthor());
    bookDto.setQuantity(book.getQuantity());
    bookDto.setPrice(book.getPrice());
    bookDto.setVersion(book.getVersion());
    return bookDto;
  }

  public static Book toEntity(BookDto bookDto) {
    Book book = new Book();
    book.setId(bookDto.getId());
    book.setName(bookDto.getName());
    book.setAuthor(bookDto.getAuthor());
    book.setQuantity(bookDto.getQuantity());
    book.setPrice(bookDto.getPrice());
    book.setVersion(bookDto.getVersion());
    return book;
  }

  public static Iterable<BookDto> toDtoList(List<Book> all) {
    return all.stream().map(BookMapper::toDto).collect(Collectors.toList());
  }
}
