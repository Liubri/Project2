package uk.ac.nulondon;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class TestRedSeam {
    public static void main(String[] args) throws IOException {

        String Filepath = "src/main/resources/Beach.png";
        BufferedImage testImage = ImageIO.read(new File(Filepath));


        Image image = new Image(testImage); // Create an instance of Image with BufferedImage
        EditingImage editingImage = new EditingImage(image);
        int width = testImage.getWidth();
        int height = testImage.getHeight();

        ArrayList<ArrayList<Integer>> redgridTest = image.energyGrid(testImage);
        ArrayList<Node> testDS = image.convertToDS(testImage);
        ArrayList<EditingImage.Pair> testSeam = editingImage.getSeam(redgridTest, width, height);

        editingImage.highLightRed(testDS, testSeam);

        BufferedImage testImage2 = editingImage.convertToBufferImage(testDS);


        String filePath = "src/main/resources/BeachRed.png"; // Change this to the desired path
        File outputImage = new File(filePath);
        ImageIO.write(testImage2,"png", outputImage);


    }

//    public static void printArrayList(ArrayList<ArrayList<Integer>> arrayList) {
//        for (ArrayList<Integer> innerList : arrayList) {
//            for (Integer value : innerList) {
//                System.out.print(value + " ");
//            }
//            System.out.println();
//        }
//    }
//    public static void main(String[] args) throws IOException {
//
//        String Filepath = "src/main/resources/Beach.png";
//        BufferedImage testImage = ImageIO.read(new File(Filepath));
//
//
//        Image image = new Image(testImage); // Create an instance of Image with BufferedImage
//        EditingImage editingImage = new EditingImage(image);
//        int width = testImage.getWidth();
//        int height = testImage.getHeight();
//
//        ArrayList<ArrayList<Integer>> bluegridTest = image.blueGrid(testImage);
//
//        printArrayList(bluegridTest);
//    }
}
