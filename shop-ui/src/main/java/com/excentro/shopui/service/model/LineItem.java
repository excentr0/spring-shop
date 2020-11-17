package com.excentro.shopui.service.model;

import com.excentro.dto.ProductDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import static lombok.EqualsAndHashCode.Exclude;

@Data
@NoArgsConstructor
public class LineItem implements Serializable {
  private Long productId;
  @Exclude private ProductDto productDto;
  @Exclude private Integer qty;

  public LineItem(ProductDto productDto, Integer qty) {
    this.productId = productDto.getId();
    this.productDto = productDto;
    this.qty = qty;
  }

  public BigDecimal getTotal() {
    return qty != null ? productDto.getPrice().multiply(new BigDecimal(qty)) : new BigDecimal(0);
  }
}
