package com.excentro.pictureservice.service;

import com.excentro.persist.model.Picture;
import com.excentro.persist.model.PictureData;
import com.excentro.persist.repo.PictureRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PictureServiceBlobImpl implements PictureService {

  private final PictureRepository pictureRepository;

  @Override
  public Optional<String> getPictureContentTypeById(long id) {
    return pictureRepository.findById(id).map(Picture::getContentType);
  }

  @Override
  public Optional<byte[]> getPictureDataById(long id) {
    return pictureRepository
        .findById(id)
        .filter(pic -> pic.getPictureData().getData() != null)
        .map(pic -> pic.getPictureData().getData());
  }

  @Override
  public PictureData createPictureData(byte[] picture) {
    return new PictureData(picture);
  }
}
