package com.excentro.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Product implements Serializable {
  private static final long serialVersionUID = -7064949892895927969L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;
  private BigDecimal price;
  private String description;

  @ManyToOne(optional = false)
  private Brand brand;

  @ManyToOne(optional = false)
  private Category category;

  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
  @JoinTable(
      name = "products_pictures",
      joinColumns = { @JoinColumn(name = "product_id") },
      inverseJoinColumns = { @JoinColumn(name = "picture_id") })
  private List<Picture> pictures;
}
