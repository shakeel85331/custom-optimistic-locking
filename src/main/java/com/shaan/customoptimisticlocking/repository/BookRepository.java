package com.shaan.customoptimisticlocking.repository;

import com.shaan.customoptimisticlocking.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  @Modifying
  @Transactional
  @Query("UPDATE Book e SET e.price = :price, e.version = :newVersion WHERE e.id = :id AND e.version = :currentVersion")
  int updateEntityWithVersion(Long id, Double price, Integer newVersion, Integer currentVersion);
}
