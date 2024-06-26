package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;


public class EditingImageTest {




//    @Test
//    void getSeamTest(){
//        List<List<Integer>> grid2 = new ArrayList<>();
//        grid2.add(List.of(5, 6, 3, 8));
//        grid2.add(List.of(4, 1, 6, 4));
//        grid2.add(List.of(3, 2, 1, 3));
//        grid2.add(List.of(8, 6, 5, 2));
//
//        int height = grid2.size();
//        int width = grid2.get(0).size();
//
//        EditingImage editingImage = new EditingImage(grid2);
//        List<EditingImage.Pair> seam = editingImage.getSeam(grid2, width, height);
//
//        Assertions.assertThat(seam.isEqualsTo([(0, 2), (1, 1), (2, 2), (3, 3)]));
//    }

//    @Test
//    void getSeamTest() {
//        List<List<Integer>> grid2 = new ArrayList<>();
//        grid2.add(List.of(5, 6, 3, 8));
//        grid2.add(List.of(4, 1, 6, 4));
//        grid2.add(List.of(3, 2, 1, 3));
//        grid2.add(List.of(8, 6, 5, 2));
//
//        int height = grid2.size();
//        int width = grid2.get(0).size();
//
//        EditingImage editingImage = new EditingImage(grid2);
//        List<EditingImage.Pair> seam = editingImage.getSeam(grid2, width, height);
//
//        // Creating the expected seam
//        List<EditingImage.Pair> expectedSeam = new ArrayList<>();
//        expectedSeam.add(new EditingImage.Pair(0, 2));
//        expectedSeam.add(new EditingImage.Pair(1, 1));
//        expectedSeam.add(new EditingImage.Pair(2, 2));
//        expectedSeam.add(new EditingImage.Pair(3, 3));
//
//        // Comparing each pair in the seam list individually
//        Assertions.assertThat(seam.size()).isEqualTo(expectedSeam.size());
//        for (int i = 0; i < seam.size(); i++) {
//            Assertions.assertThat(seam.get(i).getRow()).isEqualTo(expectedSeam.get(i).getRow());
//            Assertions.assertThat(seam.get(i).getCol()).isEqualTo(expectedSeam.get(i).getCol());
//        }
//    }

    @Test
    void getSeamTest() {
        ArrayList<ArrayList<Integer>> grid2 = new ArrayList<>();
        grid2.add(new ArrayList<>(List.of(5, 6, 3, 8)));
        grid2.add(new ArrayList<>(List.of(4, 1, 6, 4)));
        grid2.add(new ArrayList<>(List.of(3, 2, 1, 3)));
        grid2.add(new ArrayList<>(List.of(8, 6, 5, 2)));

        int height = grid2.size();
        int width = grid2.get(0).size();

        EditingImage editingImage = new EditingImage(grid2);
        List<EditingImage.Pair> seam = editingImage.getSeam(grid2, width, height);

        // Creating the expected seam
        List<EditingImage.Pair> expectedSeam = new ArrayList<>();
        expectedSeam.add(new EditingImage.Pair(3, 3));
        expectedSeam.add(new EditingImage.Pair(2, 2));
        expectedSeam.add(new EditingImage.Pair(1, 1));
        expectedSeam.add(new EditingImage.Pair(0, 2));

        // Comparing the content of the lists
        Assertions.assertThat(seam.size()).isEqualTo(expectedSeam.size());
        for (int i = 0; i < seam.size(); i++) {
            EditingImage.Pair seamPair = seam.get(i);
            EditingImage.Pair expectedPair = expectedSeam.get(i);
            Assertions.assertThat(seamPair.getRow()).isEqualTo(expectedPair.getRow());
            Assertions.assertThat(seamPair.getCol()).isEqualTo(expectedPair.getCol());
        }
    }

    @Test
    void convertToBufferImageTest() {
        BufferedImage testImage = new BufferedImage(3, 2, BufferedImage.TYPE_INT_RGB);
        testImage.setRGB(0, 0, Color.RED.getRGB());
        testImage.setRGB(1, 0, Color.GREEN.getRGB());
        testImage.setRGB(2, 0, Color.BLACK.getRGB());


        testImage.setRGB(0, 1, Color.BLUE.getRGB());
        testImage.setRGB(1, 1, Color.BLACK.getRGB());
        testImage.setRGB(2, 1, Color.BLACK.getRGB());

        Image image = new Image(testImage);

        ArrayList<Node> testDS = image.convertToDS(testImage);

        EditingImage editingImage = new EditingImage(image);

        BufferedImage finalImage = editingImage.convertToBufferImage(testDS);

        Assertions.assertThat(finalImage.getRGB(0, 0)).isEqualTo(testImage.getRGB(0, 0));
        Assertions.assertThat(finalImage.getRGB(2, 1)).isEqualTo(testImage.getRGB(2, 1));

    }

    @Test
    void testremoveBluestCol(){

    }


}
