package com.excentro.dto;

import java.io.Serializable;

import com.excentro.persist.model.Picture;
import lombok.Data;

@Data
public class ImageDto implements Serializable {
  private static final long serialVersionUID = 8827196152171240854L;
  private Long id;
  private String name;
  private String contentType;

  public ImageDto(Picture picture) {
    this.id = picture.getId();
    this.name = picture.getName();
    this.contentType = picture.getContentType();
  }
}
