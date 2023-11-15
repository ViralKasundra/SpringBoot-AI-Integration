package com.SpringSightAI.SpringSightAI.controller;

import com.SpringSightAI.SpringSightAI.service.ComputerVisionService;
import com.SpringSightAI.SpringSightAI.service.MachineLearningService;
import com.SpringSightAI.SpringSightAI.service.NLPService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tensorflow.Tensor;

@RestController
@RequestMapping("/api")
public class AIController {

    private final ComputerVisionService computerVisionService;
    private final MachineLearningService machineLearningService;
    private final NLPService nlpService;

    public AIController(
            ComputerVisionService computerVisionService,
            MachineLearningService machineLearningService,
            NLPService nlpService) {
        this.computerVisionService = computerVisionService;
        this.machineLearningService = machineLearningService;
        this.nlpService = nlpService;
    }

    @PostMapping("/processImage")
    public byte[] processImage(@RequestBody byte[] inputImage) {
        return computerVisionService.processImage(inputImage);
    }

    @PostMapping("/predict")
    public String predictFromModel(@RequestBody Tensor<?> input) {
        return machineLearningService.predictFromModel(input);
    }

    @PostMapping("/analyzeText")
    public String analyzeText(@RequestBody String inputText) {
        return nlpService.analyzeText(inputText);
    }

    // Add more API methods as needed
}
