package com.shaan.customoptimisticlocking.service;

import com.shaan.customoptimisticlocking.model.dto.BookDto;
import com.shaan.customoptimisticlocking.model.entity.Book;
import com.shaan.customoptimisticlocking.model.mapper.BookMapper;
import com.shaan.customoptimisticlocking.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

  @Value("${manual-optimistic-locking.enabled}")
  private boolean optimisticLockingEnabled;

  private final BookRepository bookRepository;

  @Transactional
  public void update(BookDto bookDto) throws InterruptedException {

    if (optimisticLockingEnabled) {
      log.info("########## Optimistic locking is enabled ##########");
      updateWithManualOptimisticLockingEnabled(bookDto);
    } else {
      log.info("########## Optimistic locking is disabled ##########");
      Thread.sleep(20000);
      bookRepository.save(BookMapper.toEntity(bookDto));
    }
  }

  private void updateWithManualOptimisticLockingEnabled(BookDto bookDto) throws InterruptedException {
    Book entity = bookRepository.findById(bookDto.getId()).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    Integer currentVersion = entity.getVersion();

    Thread.sleep(20000);

    int updateCount = bookRepository.updateEntityWithVersion(bookDto.getId(), bookDto.getPrice(), bookDto.getVersion(), currentVersion);

    if (updateCount == 0) {
      throw new OptimisticLockingFailureException("Update failed due to concurrent modification.");
    }
  }

  public void updateTest(BookDto bookDto) {
    bookRepository.save(BookMapper.toEntity(bookDto));
  }

  public Iterable<BookDto> getBooks() {
    return BookMapper.toDtoList(bookRepository.findAll());
  }

  public void init() {
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Effective Java", "Joshua Bloch", 15, 45.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Clean Code", "Robert C. Martin", 25, 50.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 10, 55.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Refactoring", "Martin Fowler", 20, 60.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Java Concurrency in Practice", "Brian Goetz", 5, 70.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Spring in Action", "Craig Walls", 30, 40.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Head First Design Patterns", "Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson", 12, 35.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Test Driven Development: By Example", "Kent Beck", 18, 45.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "The Pragmatic Programmer", "Andrew Hunt, David Thomas", 22, 55.0, 1)));
    bookRepository.save(BookMapper.toEntity(new BookDto(null, "Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", 8, 80.0, 1)));
  }


}
