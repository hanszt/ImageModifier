package controller;

import model.ImageData;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

public class Controller {

    private static Scanner input = new Scanner(System.in);

    public static void selectFunction() {
        String selector;
        do {
            pressEnterToContinue();
            System.out.print("\nPress c to create images\n" +
                    "Press s to select inputImage\n" +
                    "Press d to delete generated outputImages\n" +
                    "Press v to view methods in ImageDataController\n" +
                    "Press e to exit program.\n" +
                    "Your Input: ");
            selector = input.next();
            switch (selector) {
                case "c":
                    createImage();
                    break;
                case "s":
                    selectInputImage();
                    break;
                case "d":
                    deleteGeneratedOutputFiles();
                    break;
                case "v":
                    viewImageDataControllerMethods();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Alleen c, s, d, v of e invoeren");
                    break;
            }
        } while (true);
    }

    private static void selectInputImage() {
        String dir = System.getProperty("user.dir");
        File inputImagesDirectory = new File(dir + "/resources/input");
        try {
            String[] fileNames = inputImagesDirectory.list();
            int counter = 1;
            System.out.println("\nType the number in front of the image you want to edit: ");
            for (String filename : fileNames) {
                System.out.printf("%d %s\n", counter, filename);
                counter++;
            }
            counter = 1;
            System.out.print("Your Input: ");
            int selectImageNbr = input.nextInt();
            for (String filename : fileNames) {
                if (selectImageNbr == counter) {
                    createOutputImages(loadSelectedImage(filename));
                }
                counter++;
            }
            if (selectImageNbr > fileNames.length || selectImageNbr < 1) {
                System.out.println("Een afbeelding met dit nummer bestaat niet.");
            }
        } catch (NullPointerException e) {
            System.err.println("De input directory is niet goed gespecificeerd of er staan geen files in de input directory");
        } catch (InputMismatchException e) {
            System.err.println("Alleen een geheel getal in de lijst typen");
            input.next();
        }
    }

    private static ImageData loadSelectedImage(String inputName) {
        return ImageDataController.storeImageData(inputName);
    }

    private static void viewImageDataControllerMethods() {
        try {
            // create class object
            Class classobj = ImageDataController.class;
            // get list of declared methods
            Method[] methods = classobj.getDeclaredMethods();
            List<String> methodNameList = new ArrayList<>();
            for (Method method : methods) {
                methodNameList.add(method.getName());
            }
            Collections.sort(methodNameList);
            // get the name of every method present in the list
            System.out.printf("\nLijst met namen van methoden in %s:\n", classobj.getName());
            for (String name : methodNameList) {
                System.out.println("Name of the method: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createOutputImages(ImageData imageData) {

        System.out.printf("\n%s wordt bewerkt...\n", imageData.getName());
        ArrayList<ImageData> modifiedImageDataList = new ArrayList<>();

        modifiedImageDataList.add(imageData);
        modifiedImageDataList.add(ImageDataController.makeGrayImage(imageData));
        modifiedImageDataList.add(ImageDataController.makeBlackAndWhiteImage(imageData, 100));
        modifiedImageDataList.add(ImageDataController.makeNegativeImage(imageData));
        modifiedImageDataList.add(ImageDataController.makeImageOnlyRed(imageData));
        modifiedImageDataList.add(ImageDataController.makeImageOnlyGreen(imageData));
        modifiedImageDataList.add(ImageDataController.makeImageOnlyBlue(imageData));
        modifiedImageDataList.add(ImageDataController.makeImageAvgRed(imageData));
        modifiedImageDataList.add(ImageDataController.makeImageAvgGreen(imageData));
        modifiedImageDataList.add(ImageDataController.makeImageAvgBlue(imageData));
        modifiedImageDataList.add(ImageDataController.swapColors(imageData, -1));
        modifiedImageDataList.add(ImageDataController.swapColors(imageData, 1));

        modifiedImageDataList.add(ImageDataController.flipHalfRoundVerticalAxis(imageData));
        modifiedImageDataList.add(ImageDataController.flipImageRoundVerticalAxis(imageData));
        modifiedImageDataList.add(ImageDataController.flipHalfRoundHorizontalAxis(ImageDataController.flipHalfRoundVerticalAxis(imageData)));
        modifiedImageDataList.add(ImageDataController.flipHalfRoundHorizontalAxis(imageData));
        modifiedImageDataList.add(ImageDataController.flipImageRoundHorizontalAxis(imageData));
        modifiedImageDataList.add(ImageDataController.turn90Degrees(imageData, "left"));
        modifiedImageDataList.add(ImageDataController.turn90Degrees(imageData, "right"));

        modifiedImageDataList.add(ImageDataController.makeImageOverlayedWithGrayNoise(imageData, 20));
        modifiedImageDataList.add(ImageDataController.makeImageOverlayedWithColorNoise(imageData, 20));

        for (ImageData modifiedImage : modifiedImageDataList) {
            ImageDataController.constructImage(modifiedImage, modifiedImage.getImagenr() + modifiedImage.getName(), "jpg");
        }

        ImageDataController.writeLogOfAllPixels(imageData, "allDataof" + imageData.getName() + ".txt");
        System.out.printf("Klaar met het bewerken van %s\n\n", imageData.getName());
    }

    private static void createImage() {
        System.out.println("De afbeeldingen worden gegenereerd...");
        ArrayList<ImageData> generatedImageDataList = new ArrayList<>();

        generatedImageDataList.add(ImageDataController.generateRandomBlackWhiteValueImageData(32, 32));
        generatedImageDataList.add(ImageDataController.generateRandomGrayValueImageData(32, 32));
        generatedImageDataList.add(ImageDataController.generateRandomImageData(32, 32));
        generatedImageDataList.add(ImageDataController.generateRandomImageData(2048, 2048));

        generatedImageDataList.add(ImageDataController.generateShadedColors(64));
        generatedImageDataList.add(ImageDataController.generateShadedColors(128));
        generatedImageDataList.add(ImageDataController.generateShadedColors(256));
        generatedImageDataList.add(ImageDataController.generateShadedColors(512));
        generatedImageDataList.add(ImageDataController.generateShadedColors(1024));
        generatedImageDataList.add(ImageDataController.generateShadedColors(2048));

        generatedImageDataList.add(ImageDataController.generateAllColors(64));
        generatedImageDataList.add(ImageDataController.generateAllColors(128));
        generatedImageDataList.add(ImageDataController.generateAllColors(256));
        generatedImageDataList.add(ImageDataController.generateAllColors(512));
        generatedImageDataList.add(ImageDataController.generateAllColors(1024));
        generatedImageDataList.add(ImageDataController.generateAllColors2(256));
        generatedImageDataList.add(ImageDataController.generateAllColors3(256));

        for (ImageData imageData : generatedImageDataList) {
            ImageDataController.constructImage(imageData, imageData.getImagenr() + imageData.getName(), "jpg");
        }

        System.out.println("De afbeeldingen zijn gegenereerd.");
    }

    private static void deleteGeneratedOutputFiles() {
        //String dir = System.getProperty("user.dir");
        File outputImagesDirectory = new File("resources/output");
        try {
            File[] files = outputImagesDirectory.listFiles();
            for (File file : files) {
                System.out.println(file + " is deleted");
                if (!file.delete()) {
                    System.err.println("Failed to delete " + file);
                }
            }
            System.out.println(files.length + " files deleted.");
        } catch (NullPointerException e) {
            System.out.println("No files in directory");
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}