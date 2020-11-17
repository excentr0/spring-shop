package com.excentro.shopui.service;

import com.excentro.dto.ProductDto;
import com.excentro.shopui.service.model.LineItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface CartService extends Serializable {
  void addProductQty(ProductDto productDto, int qty);

  void removeProduct(ProductDto productDto, int qty);

  List<LineItem> getLineItems();

  BigDecimal getSubTotal();

  void updateCart(LineItem lineItem);

  void removeProduct(LineItem lineItem);
}
