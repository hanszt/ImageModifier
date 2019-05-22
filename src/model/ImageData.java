package model;

import java.util.List;

public class ImageData {
    private static int nextNumber = 1;
    private int imagenr;
    private String name;
    private List<Pixel> pixelvaluesImage;
    private int imageWidth;
    private int imageHeight;

    public ImageData(String name, List<Pixel> pixelvaluesImage, int imageWidth, int imageHeight) {
        this.name = name;
        this.pixelvaluesImage = pixelvaluesImage;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        imagenr = nextNumber++;
    }

    public int getImagenr() {
        return imagenr;
    }

    public String getName() {
        return name;
    }

    public List<Pixel> getPixelvaluesImage() {
        return pixelvaluesImage;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    @Override
    public String toString() {
        return String.format("Number: %d Name: %s, Imagewidth: %d, ImageHeigth: %d nr. of Pixels: %d",
                imagenr, name, imageWidth, imageHeight, imageWidth * imageHeight);
    }
}
