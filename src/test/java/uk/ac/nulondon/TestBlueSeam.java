package uk.ac.nulondon;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
public class TestBlueSeam {
    public static void main(String[] args) throws IOException {

        String Filepath = "src/main/resources/Beach.png";
        BufferedImage testImage = ImageIO.read(new File(Filepath));


        Image image = new Image(testImage); // Create an instance of Image with BufferedImage
        EditingImage editingImage = new EditingImage(image);

//        List<EditingImage.Pair> seam = editingImage.getSeam(image.blueGrid(testImage), image.width, image.height);


        editingImage.highLightBlue(image.leftColumn);

        editingImage.converToBufferImage(image.leftColumn);


        String filePath = "src/main/resources/BeachBlue.png"; // Change this to the desired path
        File outputImage = new File(filePath);
        ImageIO.write(testImage,"png", outputImage);


    }
}
