package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TestEditingImage {

    @Test
    public void TesthighLightBlue(){
        ArrayList<ArrayList<Integer>> grid2 = new ArrayList<>();
        grid2.add(new ArrayList<>(List.of(5, 6, 3)));
        grid2.add(new ArrayList<>(List.of(4, 1, 6)));

        int height = grid2.size();
        int width = grid2.get(0).size();

        EditingImage editingImage = new EditingImage(grid2);
        ArrayList<EditingImage.Pair> testSeam = editingImage.getSeam(grid2, width, height);

        //extected pair (1,1) and (0,2)

        BufferedImage testImage = new BufferedImage(3, 2, BufferedImage.TYPE_INT_RGB);
        testImage.setRGB(0, 0, Color.RED.getRGB());
        testImage.setRGB(1, 0, Color.GREEN.getRGB());
        testImage.setRGB(2, 0, Color.GREEN.getRGB());//change this color
        testImage.setRGB(0, 1, Color.GREEN.getRGB());
        testImage.setRGB(1, 1, Color.BLACK.getRGB());//change this color
        testImage.setRGB(2, 1, Color.BLACK.getRGB());

        Image image = new Image(testImage);

        ArrayList<Node> testDS = image.convertToDS(testImage);

        editingImage.highLightBlue(testDS, testSeam);

        Assertions.assertThat(testDS.get(0).next.next.rgb).isEqualTo(Color.BLUE);
//        Assertions.assertThat(testDS.get(1).next.rgb).isEqualTo(Color.BLUE);
//        Assertions.assertThat(testDS.get(1).next.next.rgb).isEqualTo(Color.BLACK);
    }
}
