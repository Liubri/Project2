package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import javax.imageio.stream.ImageInputStream;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageTest {


    @Test
    public void testConvertToDS(){
        BufferedImage testImage = new BufferedImage(3, 2, BufferedImage.TYPE_INT_RGB);
        testImage.setRGB(0, 0, Color.RED.getRGB());
        testImage.setRGB(1, 0, Color.GREEN.getRGB());
        testImage.setRGB(2, 0, Color.BLACK.getRGB());


        testImage.setRGB(0, 1, Color.BLUE.getRGB());
        testImage.setRGB(1, 1, Color.BLACK.getRGB());
        testImage.setRGB(2, 1, Color.BLACK.getRGB());

        Image image = new Image(testImage);

        ArrayList<Node> testDS = image.convertToDS(testImage);

        Assertions.assertThat(testDS.get(0).rgb).isEqualTo(Color.RED);
        Assertions.assertThat(testDS.get(0).x).isEqualTo(0);
        Assertions.assertThat(testDS.get(0).next.x).isEqualTo(1);
        Assertions.assertThat(testDS.get(0).next.rgb).isEqualTo(Color.GREEN);

        Assertions.assertThat(testDS.get(1).rgb).isEqualTo(Color.BLUE);
        Assertions.assertThat(testDS.get(1).next.rgb).isEqualTo(Color.BLACK);
        Assertions.assertThat(testDS.get(1).next.next.next).isEqualTo(null);
    }

    @Test
    public void testBlueGrid() {
        BufferedImage testImage = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        testImage.setRGB(0, 0, Color.RED.getRGB());
        testImage.setRGB(1, 0, Color.GREEN.getRGB());
        testImage.setRGB(0, 1, Color.BLUE.getRGB());
        testImage.setRGB(1, 1, new Color(0,94,124).getRGB());

        Image image = new Image(testImage);

        ArrayList<ArrayList<Integer>> bluegridTest = new ArrayList<>();

        bluegridTest = image.blueGrid(testImage);

        Color bluePixel = new Color(testImage.getRGB(1,0));
        int blueColor = bluePixel.getBlue();

        Assertions.assertThat(bluegridTest.get(1).get(1)).isEqualTo(124);
        Assertions.assertThat(bluegridTest.get(0).get(1)).isEqualTo(blueColor);
    }
}
