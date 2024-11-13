package com.shaan.customoptimisticlocking.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookDto {
  private Long id;
  private String name;
  private String author;
  private Integer quantity;
  private Double price;
  private Integer version;

}
