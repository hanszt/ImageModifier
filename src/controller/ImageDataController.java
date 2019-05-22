package controller;

import model.ImageData;
import model.Pixel;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ImageDataController {

    public static ImageData storeImageData(String inputName) {
        ImageData imagedata = null;
        File file = new File("resources/input/" + inputName);
        String name = inputName.replace(".jpg", "");
        try {
            //read image file
            BufferedImage image = ImageIO.read(file);
            ArrayList<Pixel> pixelDataImage = new ArrayList<>();

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    int redbytevalue = (rgb & 0x00FF0000) >> 16;
                    int greenbytevalue = (rgb & 0x0000FF00) >> 8;
                    int bluebytevalue = rgb & 0x000000FF;

                    pixelDataImage.add(new Pixel(x, y, redbytevalue, greenbytevalue, bluebytevalue));
                }
            }
            imagedata = new ImageData(name, pixelDataImage, image.getWidth(), image.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagedata;
    }

    public static void constructImage(ImageData imageData, String outputName, String formatName) {
        BufferedImage image = new BufferedImage(imageData.getImageWidth(), imageData.getImageHeight(), BufferedImage.TYPE_INT_RGB);
        for (Pixel pixel : imageData.getPixelvaluesImage()) {
            int rgb = pixel.getRedValue();
            rgb = (rgb << 8) + pixel.getGreenValue();
            rgb = (rgb << 8) + pixel.getBlueValue();
            image.setRGB(pixel.getXposition(), pixel.getYposition(), rgb);
        }
        File outputFile = new File("resources/output/" + outputName + "." + formatName);
        try {
            ImageIO.write(image, formatName, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImageData generateRandomGrayValueImageData(int width, int height) {
        final int MAX_VALUE_RGB = 255;
        List<Pixel> pixels = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = (int) (Math.random() * MAX_VALUE_RGB);
                pixels.add(new Pixel(x, y, rgb, rgb, rgb));
            }
        }
        return new ImageData("Gray_Random", pixels, width, height);
    }

    public static ImageData generateRandomBlackWhiteValueImageData(int width, int height) {
        final int MAX_VALUE_RGB = 255;
        int rgb;
        List<Pixel> pixels = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (Math.random() < 0.5) {
                    rgb = MAX_VALUE_RGB;
                } else {
                    rgb = 0;
                }
                pixels.add(new Pixel(x, y, rgb, rgb, rgb));
            }
        }
        return new ImageData("BW_Random", pixels, width, height);
    }

    public static ImageData generateRandomImageData(int width, int height) {
        final int MAX_VALUE_RGB = 255;
        List<Pixel> pixels = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = (int) (Math.random() * MAX_VALUE_RGB);
                int g = (int) (Math.random() * MAX_VALUE_RGB);
                int b = (int) (Math.random() * MAX_VALUE_RGB);
                pixels.add(new Pixel(x, y, r, g, b));
            }
        }
        return new ImageData("Color_Random", pixels, width, height);
    }

    public static ImageData generateShadedColors(int sideLength) {
        final int MAX_VALUE_RGB = 255;
        int r = 0;
        int g = 0;
        int b = 0;
        List<Pixel> pixels = new ArrayList<>();
        for (int y = 0; y < sideLength; y++) {
            r++;
            if (r >= MAX_VALUE_RGB) r = 0;
            for (int x = 0; x < sideLength; x++) {
                g++;
                b++;
                if (g >= MAX_VALUE_RGB) g = 0;
                if (b >= MAX_VALUE_RGB) b = 0;
                //System.out.printf("r %d, g %d, b %d\n",r, g, b);
                pixels.add(new Pixel(x, y, r, g, b));
            }
        }
        return new ImageData("ShadedColors", pixels, sideLength, sideLength);
    }

    public static ImageData generateAllColors(int sideLength) {
        final int MAX_VALUE_RGB = 255;
        int r = 0;
        int g = 0;
        int b = 0;
        List<Pixel> pixels = new ArrayList<>();
        for (int y = 0; y < sideLength; y++) {
            r++;
            for (int x = 0; x < sideLength; x++) {
                g++;
                b++;
                //System.out.printf("r %d, g %d, b %d\n",r, g, b);
                pixels.add(new Pixel(x, y, r, g, b));
            }
        }
        return new ImageData("AllColors", pixels, sideLength, sideLength);
    }

    public static ImageData generateAllColors2(int sideLength) {
        final int MAX_VALUE_RGB = 255;
        int r = 0;
        int g = 0;
        int b = 0;
        List<Pixel> pixels = new ArrayList<>();
        for (int y = 0; y < sideLength; y++) {
            g++;
            for (int x = 0; x < sideLength; x++) {
                b++;
                r++;
                //System.out.printf("r %d, g %d, b %d\n",r, g, b);
                pixels.add(new Pixel(x, y, r, g, b));
            }
        }
        return new ImageData("AllColors2", pixels, sideLength, sideLength);
    }

    public static ImageData generateAllColors3(int sideLength) {
        final int MAX_VALUE_RGB = 255;
        int r = 0;
        int g = 0;
        int b = 0;
        List<Pixel> pixels = new ArrayList<>();
        for (int y = 0; y < sideLength; y++) {
            b++;
            for (int x = 0; x < sideLength; x++) {
                r++;
                g++;
                //System.out.printf("r %d, g %d, b %d\n",r, g, b);
                pixels.add(new Pixel(x, y, r, g, b));
            }
        }
        return new ImageData("AllColors3", pixels, sideLength, sideLength);
    }

    public static ImageData makeGrayImage(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int avg = (int) ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), avg, avg, avg));
        }
        return new ImageData("Gray" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeBlackAndWhiteImage(ImageData imageData, int cutoffValueRGB) {
        final int MAX_VALUE_RGB = 255;
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int blackOrWhite = ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3) > cutoffValueRGB ? 0 : MAX_VALUE_RGB;
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), blackOrWhite, blackOrWhite, blackOrWhite));
        }
        return new ImageData("BlackAndWhite" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeNegativeImage(ImageData imageData) {
        final int MAX_VALUE_RGB = 255;
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newRed = MAX_VALUE_RGB - pixel.getRedValue();
            int newGreen = MAX_VALUE_RGB - pixel.getGreenValue();
            int newBlue = MAX_VALUE_RGB - pixel.getBlueValue();
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), newRed, newGreen, newBlue));
        }
        return new ImageData("Negative" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageAvgRed(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newRed = (int) ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), newRed, pixel.getGreenValue(), pixel.getBlueValue()));
        }
        return new ImageData("NoRed" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageAvgGreen(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newGreen = (int) ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), pixel.getRedValue(), newGreen, pixel.getBlueValue()));
        }
        return new ImageData("NoGreen" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageAvgBlue(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newBlue = (int) ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), pixel.getRedValue(), pixel.getGreenValue(), newBlue));
        }
        return new ImageData("NoBlue" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageOnlyRed(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newAvg = (int) ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), pixel.getRedValue(), newAvg, newAvg));
        }
        return new ImageData("OnlyRed" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageOnlyGreen(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newAvg = (int) ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), newAvg, pixel.getGreenValue(), newAvg));
        }
        return new ImageData("OnlyGreen" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageOnlyBlue(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newAvg = (int) ((double) (pixel.getRedValue() + pixel.getGreenValue() + pixel.getBlueValue()) / 3);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), newAvg, newAvg, pixel.getBlueValue()));
        }
        return new ImageData("OnlyBlue" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    // zero for smame colors, less than zero for negativecolorswap, greater than zero for positive colorswap
    public static ImageData swapColors(ImageData imageData, int colorSelector) {
        int newRed;
        int newGreen;
        int newBlue;
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            if (colorSelector == 0) {
                newRed = pixel.getRedValue();
                newGreen = pixel.getGreenValue();
                newBlue = pixel.getBlueValue();
            } else if (colorSelector < 0) {
                newRed = pixel.getGreenValue();
                newGreen = pixel.getBlueValue();
                newBlue = pixel.getRedValue();
            } else {
                newRed = pixel.getBlueValue();
                newGreen = pixel.getRedValue();
                newBlue = pixel.getGreenValue();
            }
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), newRed, newGreen, newBlue));
        }
        return new ImageData("Colorswap" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageOverlayedWithColorNoise(ImageData imageData, int noiseStrengthPerc) {
        final int MAX_VALUE_RGB = 255;
        final int PERC = 100;
        int noiseStrength = (int) (((double) noiseStrengthPerc) / PERC * MAX_VALUE_RGB);
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int newRed = pixel.getRedValue() + (int) (Math.random() * noiseStrength);
            int newGreen = pixel.getGreenValue() + (int) (Math.random() * noiseStrength);
            int newBlue = pixel.getBlueValue() + (int) (Math.random() * noiseStrength);
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), newRed, newGreen, newBlue));
        }
        return new ImageData("Noise" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData makeImageOverlayedWithGrayNoise(ImageData imageData, int noiseStrengthPerc) {
        final int MAX_VALUE_RGB = 255;
        final int PERC = 100;
        int noiseStrength = (int) (((double) noiseStrengthPerc) / PERC * MAX_VALUE_RGB);
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int rgb = (int) (Math.random() * noiseStrength);
            int newRed = pixel.getRedValue() + rgb;
            int newGreen = pixel.getGreenValue() + rgb;
            int newBlue = pixel.getBlueValue() + rgb;
            newPixels.add(new Pixel(pixel.getXposition(), pixel.getYposition(), newRed, newGreen, newBlue));
        }
        return new ImageData("GrayNoise" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData flipImageRoundVerticalAxis(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        int newXPosition;
        for (Pixel pixel : pixels) {
            newXPosition = (imageData.getImageWidth() - 1) - pixel.getXposition();
            newPixels.add(new Pixel(newXPosition, pixel.getYposition(), pixel.getRedValue(), pixel.getGreenValue(), pixel.getBlueValue()));
        }
        return new ImageData("FlippedImage" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData flipImageRoundHorizontalAxis(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        int newYPosition;
        for (Pixel pixel : pixels) {
            newYPosition = (imageData.getImageHeight() - 1) - pixel.getYposition();
            newPixels.add(new Pixel(pixel.getXposition(), newYPosition, pixel.getRedValue(), pixel.getGreenValue(), pixel.getBlueValue()));
        }
        return new ImageData("FlippedImage_h" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData flipHalfRoundVerticalAxis(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int shifter = (imageData.getImageWidth() % 2 == 0) ? 2 : 1;
            int newXPosition = pixel.getXposition() % 2 == 0 ?
                    imageData.getImageWidth() - shifter - pixel.getXposition() : pixel.getXposition();
            newPixels.add(new Pixel(newXPosition, pixel.getYposition(),
                    pixel.getRedValue(), pixel.getGreenValue(), pixel.getBlueValue()));
        }
        return new ImageData("HalfFlippedImage" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData flipHalfRoundHorizontalAxis(ImageData imageData) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        for (Pixel pixel : pixels) {
            int shifter = imageData.getImageHeight() % 2 == 0 ? 2 : 1;
            int newYPosition = pixel.getYposition() % 2 == 0 ?
                    imageData.getImageHeight() - shifter - pixel.getYposition() : pixel.getYposition();
            newPixels.add(new Pixel(pixel.getXposition(), newYPosition,
                    pixel.getRedValue(), pixel.getGreenValue(), pixel.getBlueValue()));
        }
        return new ImageData("HalfFlippedImage_h" + imageData.getName(), newPixels, imageData.getImageWidth(), imageData.getImageHeight());
    }

    public static ImageData turn90Degrees(ImageData imageData, String leftOrRight) {
        List<Pixel> pixels = imageData.getPixelvaluesImage();
        List<Pixel> newPixels = new ArrayList<>();
        int newImageHeight = imageData.getImageWidth();
        int newImageWidth = imageData.getImageHeight();
        for (Pixel pixel : pixels) {
            int newYPosition = pixel.getXposition();
            int newXPosition = leftOrRight.equals("right") ? (newImageWidth - 1) - pixel.getYposition() : pixel.getYposition();
            //int newXPosition = (newImageWidth - 1) - pixel.getYposition();
            newPixels.add(new Pixel(newXPosition, newYPosition,
                    pixel.getRedValue(), pixel.getGreenValue(), pixel.getBlueValue()));
        }
        return new ImageData("Turned90deg" + leftOrRight + imageData.getName(),
                newPixels, newImageWidth, newImageHeight);
    }

    public static void writeLogOfAllPixels(ImageData imageData, String logName) {
        File filenaam = new File("resources/output/" + logName);
        try {
            PrintWriter printWriter = new PrintWriter(filenaam);
            String title = String.format("De RGB waarden per pixel van alle pixels in %s", imageData.getName());
            printWriter.println(title);

            int teller = 1;
            for (Pixel pixel : imageData.getPixelvaluesImage()) {
                String result = String.format("nr: %4d, %s", teller, pixel);
                printWriter.println(result);
                teller++;
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeLogOfSpecificColor(ImageData imageData, String logName,
                                               int redbytevalueToFind, int greenbytevalueToFind, int bluebytevalueToFind) {
        File filenaam = new File("resources/output/" + logName);

        try {
            PrintWriter printWriter = new PrintWriter(filenaam);
            String title = String.format("De RGB waarden per pixel van specifieke kleur in %s", imageData.getName());

            printWriter.println(title);
            int teller = 1;
            for (Pixel pixel : imageData.getPixelvaluesImage()) {
                if (pixel.getRedValue() == redbytevalueToFind &&
                        pixel.getGreenValue() == greenbytevalueToFind &&
                        pixel.getBlueValue() == bluebytevalueToFind) {
                    String result = String.format("nr %4d:, %s", teller, pixel);
                    printWriter.println(result);
                    teller++;
                }
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}




