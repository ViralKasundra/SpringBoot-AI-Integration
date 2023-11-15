package com.SpringSightAI.SpringSightAI.repository;

import com.SpringSightAI.SpringSightAI.model.ProcessedImage;
import org.springframework.data.repository.CrudRepository;

public interface ProcessedImageRepository extends CrudRepository<ProcessedImage, Long> {
    // Add custom queries if needed
}
