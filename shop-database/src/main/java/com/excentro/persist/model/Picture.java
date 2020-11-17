package com.excentro.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Picture implements Serializable {
  private static final long serialVersionUID = 7902442061463838121L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name; // filename
  private String contentType;

  @OneToOne(
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      optional = false,
      orphanRemoval = true)
  private PictureData pictureData;

  @ManyToMany(mappedBy = "pictures")
  private List<Product> products;

  public Picture(String name, String contentType, PictureData pictureData) {
    this.name = name;
    this.contentType = contentType;
    this.pictureData = pictureData;
  }
}
