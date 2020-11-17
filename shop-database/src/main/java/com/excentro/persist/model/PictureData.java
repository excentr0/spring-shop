package com.excentro.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class PictureData implements Serializable {
  private static final long serialVersionUID = 8208937984225068851L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Lob
  @Column(nullable = false, length = 33554430)
  private byte[] data;

  public PictureData(byte[] bytes) {
    this.data = bytes;
  }
}
