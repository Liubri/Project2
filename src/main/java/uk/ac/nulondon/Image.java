package uk.ac.nulondon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.Color;
public class Image {
    List<Node> pixelList;
    int width;
    int height;

    static Image image;
    File ogFile;
    BufferedImage oldImg;
    BufferedImage newImg;

    public Image(String filePath) {
        try {
            ogFile = new File(filePath);
            oldImg = ImageIO.read(ogFile);

        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }

        width = oldImg.getWidth();
        height = oldImg.getHeight();
        //image = new Image(convertToGrid(oldImg));
    }
    public void createImage(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {

            Color colorCol = new Color(image.getRGB(0, y));

            // Node leftMostNode = new Node(0, 0 + i,  image.getRGB(0,));
            Node leftMostNode = new Node(0, y, colorCol);

            pixelList.add(leftMostNode);
            for (int x = 0; x < image.getWidth(); x++) {

                Color colorRow = new Color(image.getRGB(x,y ));

                leftMostNode.addNode(x, 0, colorRow);
            }
        }
    }

    

}
