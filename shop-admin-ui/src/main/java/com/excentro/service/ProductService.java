package com.excentro.service;

import com.excentro.dto.ProductDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.io.IOException;
import java.util.List;

public interface ProductService {
  List<ProductDto> findAll();

  void save(ProductDto product) throws IOException, ChangeSetPersister.NotFoundException;

  ProductDto findById(Long id);
}
