package uk.ac.nulondon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Image {
    ArrayList<Node> leftColumn;
    public static int width;
    public static int height;

    static Image image;
    File ogFile;
    BufferedImage oldImg;
    BufferedImage newImg;

    static ArrayList<ArrayList<Integer>> energyGrid;

    static ArrayList<ArrayList<Integer>> blueGrid;

    public Image(String filePath) {
        try {
            ogFile = new File(filePath);
            oldImg = ImageIO.read(ogFile);
            width = oldImg.getWidth();
            height = oldImg.getHeight();
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
    }

    public Image(BufferedImage oldImg){
        this.oldImg = oldImg;
        width = oldImg.getWidth();
        height = oldImg.getHeight();
    }


//    public ArrayList<Node> convertToDS (BufferedImage image) {
//        leftColumn = new ArrayList<>();
//        for (int y = 0; y < image.getHeight(); y++) {
//            Color colorCol = new Color(image.getRGB(0, y));
//            Node leftMostNode = new Node(0, y, colorCol);
//            leftColumn.add(leftMostNode);
//            for (int x = 1; x < image.getWidth(); x++) {
//                Color colorRow = new Color(image.getRGB(x,y ));
//                leftMostNode.addNode(x, 0, colorRow);
//            }
//        }
//        return leftColumn;
//    }

//    public ArrayList<Node> convertToDS(BufferedImage image) {
//        leftColumn = new ArrayList<>();
//        for (int y = 0; y < image.getHeight(); y++) {
//            Node prevNode = null; // Keep track of the previous node in the row
//            for (int x = 0; x < image.getWidth(); x++) {
//                Color colorRow = new Color(image.getRGB(x, y));
//                Node newNode = new Node(x, y, colorRow);
//                if (x == 0) {
//                    leftColumn.add(newNode); // Add the first node of each row to leftColumn
//                }
//                if (prevNode != null) {
//                    prevNode.addNode(x,y,colorRow); // Link the new node to the previous node
//                }
//                prevNode = newNode; // Update the previous node
//
//            }
//        }
//        return leftColumn;
//    }
    public ArrayList<Node> convertToDS(BufferedImage image) {
        ArrayList<Node> leftColumn = new ArrayList<>();

        // Iterate over each pixel in the image
        for (int y = 0; y < image.getHeight(); y++) {
            Color colorCol = new Color(image.getRGB(0, y)); // Color of the leftmost pixel in the row
            Node leftMostNode = new Node(0, y, colorCol); // Create a new node for the leftmost pixel
            leftColumn.add(leftMostNode); // Add the leftmost node to the left column

            // Iterate over each pixel in the row (excluding the leftmost pixel)
            for (int x = 1; x < image.getWidth(); x++) {
                Color colorRow = new Color(image.getRGB(x, y)); // Color of the current pixel
                leftMostNode.addNode(x, y, colorRow); // Add a new node for the current pixel to the leftmost node
            }
        }

        return leftColumn;
    }





    public ArrayList<Node> getLeftColumnTest() {
        return leftColumn;
    }



    public BufferedImage getBufferedImage() {
        return oldImg;
    }

    public ArrayList<ArrayList<Integer>> energyGrid (BufferedImage img) {
        energyGrid = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            ArrayList<Integer> rowPixels = new ArrayList<Integer>();
            energyGrid.add(rowPixels);
            for (int x = 0; x < width; x++) {
                int calculatedEnergy = calcEnergy(img, x, y);
                rowPixels.add(calculatedEnergy);
            }
        }
        return energyGrid;
    }

    public static int calcEnergy(BufferedImage img, int x, int y) {
        Color imgColor = new Color(img.getRGB(x, y));
        int green = imgColor.getGreen();
        int blue = imgColor.getBlue();
        int red = imgColor.getRed();

        int energy = (green + blue + red)/3;

        return energy;
    }

    public ArrayList<ArrayList<Integer>> blueGrid (BufferedImage img) {
        blueGrid = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            ArrayList<Integer> rowPixels = new ArrayList<>();

            for (int x = 0; x < width; x++) {
                Color imgColor = new Color(img.getRGB(x, y));
                int blueValue = imgColor.getBlue();
                rowPixels.add(blueValue);
            }

            blueGrid.add(rowPixels);
        }
        return blueGrid;

    }



}
