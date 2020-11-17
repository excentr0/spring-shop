package com.excentro.pictureservice.service;

import com.excentro.persist.model.PictureData;

import java.util.Optional;

public interface PictureService {
  Optional<String> getPictureContentTypeById(long id);

  Optional<byte[]> getPictureDataById(long id);

  PictureData createPictureData(byte[] picture);
}
