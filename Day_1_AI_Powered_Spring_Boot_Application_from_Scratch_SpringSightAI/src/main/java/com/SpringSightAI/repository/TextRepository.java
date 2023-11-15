package com.SpringSightAI.SpringSightAI.repository;

import com.SpringSightAI.SpringSightAI.model.Text;
import org.springframework.data.repository.CrudRepository;

public interface TextRepository extends CrudRepository<Text, Long> {
    // Add custom queries if needed
}
