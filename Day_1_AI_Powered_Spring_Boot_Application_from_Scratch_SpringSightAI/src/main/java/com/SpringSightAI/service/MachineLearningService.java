package com.SpringSightAI.SpringSightAI.service;

import com.SpringSightAI.SpringSightAI.model.ProcessedImage;
import com.SpringSightAI.SpringSightAI.repository.ProcessedImageRepository;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageProcessingService {

    private final ProcessedImageRepository processedImageRepository;

    @Autowired
    public ImageProcessingService(ProcessedImageRepository processedImageRepository) {
        this.processedImageRepository = processedImageRepository;
    }

    /**
     * Save a processed image to the database.
     *
     * @param processedImage Processed image to be saved.
     * @return Saved ProcessedImage entity.
     */
    public ProcessedImage saveProcessedImage(byte[] processedImage) {
        ProcessedImage savedProcessedImage = processedImageRepository.save(new ProcessedImage(processedImage));
        return savedProcessedImage;
    }

    /**
     * Retrieve a processed image from the database by its ID.
     *
     * @param id ID of the processed image.
     * @return ProcessedImage entity.
     */
    public Optional<ProcessedImage> getProcessedImageById(Long id) {
        return processedImageRepository.findById(id);
    }

    /**
     * Apply a simple image processing operation (e.g., resizing) using OpenCV.
     *
     * @param inputImage Input image as a byte array.
     * @return Processed image as a byte array.
     */
    public byte[] applySimpleImageProcessing(byte[] inputImage) {
        // Convert byte array to OpenCV Mat
        Mat originalImage = AppUtils.byteArrayToMat(inputImage);

        // Apply image processing (e.g., resizing)
        Mat processedImage = resizeImage(originalImage, new Size(300, 300));

        // Convert processed image back to byte array
        return AppUtils.matToByteArray(processedImage);
    }

    /**
     * Resize an image using OpenCV.
     *
     * @param inputImage Input image as a Mat.
     * @param newSize    New size for the image.
     * @return Resized image as a Mat.
     */
    private Mat resizeImage(Mat inputImage, Size newSize) {
        Mat resizedImage = new Mat();
        Imgproc.resize(inputImage, resizedImage, newSize);
        return resizedImage;
    }

    // You can add more methods for additional image processing logic here
}
