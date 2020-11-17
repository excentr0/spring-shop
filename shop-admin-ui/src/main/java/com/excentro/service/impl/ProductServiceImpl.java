package com.excentro.service.impl;

import com.excentro.dto.ProductDto;
import com.excentro.persist.model.Picture;
import com.excentro.persist.model.PictureData;
import com.excentro.persist.model.Product;
import com.excentro.persist.repo.ProductRepository;
import com.excentro.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  @Override
  public List<ProductDto> findAll() {
    return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
  }

  @Override
  public ProductDto findById(Long id) {
    return productRepository.findById(id).map(ProductDto::new).orElse(new ProductDto());
  }

  /** Сохраняем товар. */
  @Override
  public void save(ProductDto productDto) throws ChangeSetPersister.NotFoundException, IOException {
    Product product =
        (productDto.getId() != null)
            ? productRepository
                .findById(productDto.getId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new)
            : new Product();

    product.setName(productDto.getName());
    product.setCategory(productDto.getCategory());
    product.setBrand(productDto.getBrand());
    product.setPrice(productDto.getPrice());
    product.setDescription(productDto.getDescription());

    if (productDto.getNewPictures() != null) {
      for (MultipartFile newPicture : productDto.getNewPictures()) {
        log.info(
            "Product {} file {} size {}",
            product.getId(),
            newPicture.getOriginalFilename(),
            newPicture.getSize());

        if (product.getPictures() == null) {
          product.setPictures(new ArrayList<>());
        }

        product
            .getPictures()
            .add(
                new Picture(
                    newPicture.getOriginalFilename(),
                    newPicture.getContentType(),
                    new PictureData(newPicture.getBytes())));
      }
    }
    productRepository.save(product);
  }
}
