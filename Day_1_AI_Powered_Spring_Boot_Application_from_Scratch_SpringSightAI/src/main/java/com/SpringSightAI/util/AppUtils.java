package com.SpringSightAI.SpringSightAI.util;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class AppUtils {

    /**
     * Convert a byte array to an OpenCV Mat.
     *
     * @param byteArray Input byte array.
     * @return OpenCV Mat.
     */
    public static Mat byteArrayToMat(byte[] byteArray) {
        Mat mat = Imgcodecs.imdecode(new MatOfByte(byteArray), Imgcodecs.IMREAD_UNCHANGED);
        if (mat.channels() == 4) {
            // Convert from BGRA to BGR if necessary
            Imgcodecs.cvtColor(mat, mat, Imgcodecs.COLOR_BGRA2BGR);
        }
        return mat;
    }

    /**
     * Convert an OpenCV Mat to a byte array.
     *
     * @param mat OpenCV Mat.
     * @return Byte array.
     */
    public static byte[] matToByteArray(Mat mat) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, matOfByte);
        return matOfByte.toArray();
    }
}
