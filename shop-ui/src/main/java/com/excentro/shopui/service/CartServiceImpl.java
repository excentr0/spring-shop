package com.excentro.shopui.service;

import com.excentro.dto.ProductDto;
import com.excentro.shopui.service.model.LineItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

  private static final long serialVersionUID = -4917254942170836488L;
  private final Map<LineItem, Integer> lineItems;

  public CartServiceImpl() {
    this.lineItems = new HashMap<>();
  }

  @Override
  public void addProductQty(ProductDto productDto, int qty) {
    LineItem lineItem = new LineItem(productDto, qty);
    lineItems.merge(lineItem, qty, Integer::sum);
  }

  @Override
  public void removeProduct(ProductDto productDto, int qty) {
    LineItem lineItem = new LineItem(productDto, qty);
    int currentQty = lineItems.getOrDefault(lineItem, 0);
    if (currentQty - qty > 0) {
      lineItems.merge(lineItem, 0, (a, b) -> a - b);
    } else {
      lineItems.remove(lineItem);
    }
  }

  @Override
  public List<LineItem> getLineItems() {
    lineItems.forEach(LineItem::setQty);
    return new ArrayList<>(lineItems.keySet());
  }

  @Override
  @JsonIgnore
  public BigDecimal getSubTotal() {
    lineItems.forEach(LineItem::setQty);
    return lineItems.keySet().stream()
        .map(LineItem::getTotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Override
  public void updateCart(LineItem lineItem) {
    addProductQty(lineItem.getProductDto(), lineItem.getQty());
  }

  @Override
  public void removeProduct(LineItem lineItem) {
    removeProduct(lineItem.getProductDto(), lineItem.getQty());
  }
}
