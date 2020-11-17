package com.excentro.persist.repo;

import com.excentro.persist.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {}
