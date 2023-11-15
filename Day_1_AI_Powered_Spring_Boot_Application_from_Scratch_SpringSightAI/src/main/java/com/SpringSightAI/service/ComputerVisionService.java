package com.SpringSightAI.SpringSightAI.service;

import com.SpringSightAI.SpringSightAI.model.Image;
import com.SpringSightAI.SpringSightAI.repository.ImageRepository;
import com.SpringSightAI.SpringSightAI.util.AppUtils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerVisionService {

    private final ImageRepository imageRepository;

    @Autowired
    public ComputerVisionService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Apply a simple image processing operation (e.g., resizing) using OpenCV and save the processed image.
     *
     * @param inputImage Input image as a byte array.
     * @return Processed image as a byte array.
     */
    public byte[] processAndSaveImage(byte[] inputImage) {
        // Convert byte array to OpenCV Mat
        Mat originalImage = AppUtils.byteArrayToMat(inputImage);

        // Apply image processing (e.g., resizing)
        Mat processedImage = resizeImage(originalImage, new Size(300, 300));

        // Save the processed image to the database
        Image savedImage = saveProcessedImageToDatabase(processedImage);

        // Convert processed image back to byte array
        return AppUtils.matToByteArray(savedImage.getProcessedData());
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

    /**
     * Save the processed image to the database using the ImageRepository.
     *
     * @param processedImage Processed image as a Mat.
     * @return Saved Image entity.
     */
    private Image saveProcessedImageToDatabase(Mat processedImage) {
        // Convert Mat to byte array and create an Image entity
        byte[] processedData = AppUtils.matToByteArray(processedImage);
        return imageRepository.save(new Image(processedData));
    }
}
