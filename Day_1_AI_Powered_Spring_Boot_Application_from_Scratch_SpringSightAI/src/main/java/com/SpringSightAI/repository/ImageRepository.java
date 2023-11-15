package com.SpringSightAI.SpringSightAI.repository;

import com.SpringSightAI.SpringSightAI.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
    // Add custom queries if needed
}
