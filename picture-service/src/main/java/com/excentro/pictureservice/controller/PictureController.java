package com.excentro.pictureservice.controller;

import com.excentro.persist.model.Picture;
import com.excentro.persist.repo.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/picture")
@AllArgsConstructor
public class PictureController {
  private final PictureRepository pictureRepository;

  @GetMapping("/{pictureId}")
  public void downloadProductPicture(@PathVariable Long pictureId, HttpServletResponse response)
      throws IOException {
    log.info("Downloading image {}", pictureId);

    Optional<Picture> picture = pictureRepository.findById(pictureId);

    if (picture.isPresent()) {
      response.setContentType(picture.get().getContentType());
      response.getOutputStream().write(picture.get().getPictureData().getData());
      return;
    }
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
  }
}
