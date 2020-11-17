package com.excentro.dto;

import com.excentro.persist.model.Brand;
import com.excentro.persist.model.Category;
import com.excentro.persist.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {
  private Long id;
  private String name;
  private BigDecimal price;
  private String description;
  private Brand brand;
  private Category category;
  private List<ImageDto> pictures;
  private transient MultipartFile[] newPictures;

  public ProductDto(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.description = product.getDescription();
    this.brand = product.getBrand();
    this.category = product.getCategory();
    this.pictures = product.getPictures().stream().map(ImageDto::new).collect(Collectors.toList());
  }
}
