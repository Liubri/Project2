package uk.ac.nulondon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Image {
//    public static ArrayList<Node> leftColumn;

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


        //image = new Image(convertToGrid(oldImg));
    }





    //Data Structure for Image
    public Image (BufferedImage image) {
         leftColumn = new ArrayList<>();
        for (int y = 0; y < image.getHeight(); y++) {

            Color colorCol = new Color(image.getRGB(0, y));

            // Node leftMostNode = new Node(0, 0 + i,  image.getRGB(0,));
            Node leftMostNode = new Node(0, y, colorCol);

            leftColumn.add(leftMostNode);
            for (int x = 0; x < image.getWidth(); x++) {

                Color colorRow = new Color(image.getRGB(x,y ));

                leftMostNode.addNode(x, 0, colorRow);
            }
        }
    }

    public ArrayList<Node> getLeftColumnTest() {
        return leftColumn;
    }



    public BufferedImage getBufferedImage() {

        return oldImg;
    }

    public ArrayList<ArrayList<Integer>> energyGrid (BufferedImage img) {
        energyGrid = new ArrayList<>(height);

        for (int y = 0; y < height; y++) {
            ArrayList<Integer> rowPixels = new ArrayList<Integer>(width);
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
        blueGrid = new ArrayList<>(height);

        for (int y = 0; y < height; y++) {
            ArrayList<Integer> rowPixels = new ArrayList<Integer>(width);
            blueGrid.add(rowPixels);
            for (int x = 0; x < width; x++) {
                Color imgColor = new Color(img.getRGB(x, y));
                int blueValue = imgColor.getBlue();
                rowPixels.add(blueValue);
            }
        }
        return blueGrid;
    }



}
