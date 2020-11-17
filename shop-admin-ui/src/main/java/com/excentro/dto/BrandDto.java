package com.excentro.dto;

import com.excentro.persist.model.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandDto {
  private Long id;
  private String name;

  public BrandDto(Brand brand) {
    this.id = brand.getId();
    this.name = brand.getName();
  }
}
