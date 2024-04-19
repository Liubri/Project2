package uk.ac.nulondon;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TestGettingSeam {

    public static void main(String[] args) {
        TestGettingSeam testGettingSeam = new TestGettingSeam();
        ArrayList<ArrayList<Integer>> grid2 = new ArrayList<>();
        grid2.add(new ArrayList<>(List.of(5, 6, 3, 8)));
        grid2.add(new ArrayList<>(List.of(4, 1, 6, 4)));
        grid2.add(new ArrayList<>(List.of(3, 2, 1, 3)));
        grid2.add(new ArrayList<>(List.of(8, 6, 5, 2)));

        int height = grid2.size();
        int width = grid2.get(0).size();

        EditingImage editingImage = new EditingImage(grid2);
        ArrayList<EditingImage.Pair> testSeam = editingImage.getSeam(grid2, width, height);

        System.out.println(testGettingSeam.getCoor(testSeam));
    }

    public String getCoor(ArrayList<EditingImage.Pair> seam) {
        StringBuilder coordinates = new StringBuilder();
        for (EditingImage.Pair coord : seam) {
            int x = coord.getCol();
            int y = coord.getRow();
            coordinates.append("(").append(y).append(",").append(x).append(") ");
        }
        return coordinates.toString();
    }


}
