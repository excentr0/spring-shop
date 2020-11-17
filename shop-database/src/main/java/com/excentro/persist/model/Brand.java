package com.excentro.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Brand implements Serializable {
  private static final long serialVersionUID = -5752788187754263038L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
  private List<Product> products;

  @Override
  public String toString() {
    return name;
  }
}
