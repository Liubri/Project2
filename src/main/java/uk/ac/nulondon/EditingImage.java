package uk.ac.nulondon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class EditingImage {

    private Image img;
    private List<List<Integer>> grid;

    static int counter = 0;

    public EditingImage(Image img) {
        this.img = img;
    }

    public EditingImage(List<List<Integer>> grid){
        this.grid = grid;
    }



//
//    List<Integer> previous = new ArrayList<>();
//    List<Integer> prevSeams = new ArrayList<>();
//    List<Integer> currentVal = new ArrayList<>();
//    List<Integer> currentSeams = new ArrayList<>();
//
//    public ArrayList<Integer>() seamCarving(ArrayList<ArrayList<Integer>> energyGrid){
//
//    }

    public static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return "(" + row + ", " + col + ")";
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    /**
     * increases count, called when highlighting, deleting, or undoing
     * @return
     */
    public int counter() {
        counter++;
        return counter;
    }

    /**
     * Saves the new image after every change
     * @param newBuffer
     * @param counter
     */
    public void saveNewImage(BufferedImage newBuffer, int counter, boolean finalVersion) {
        try {
            if (!finalVersion) {
                // Write the BufferedImage to a PNG file
                File f = new File("src/main/java/uk/ac/nulondon/tempImg" + counter + ".png");
                ImageIO.write(newBuffer, "png", f);
                System.out.println("Image saved successfully");
            }

            else {
                File f = new File("src/main/java/uk/ac/nulondon/newImg" + ".png");
                ImageIO.write(newBuffer, "png", f);
                System.out.println("Image saved successfully");
            }
        } catch (IOException e) {
            System.out.println("Error with file: " + e.getMessage());
        }
    }



    //Issue with looping
//    public BufferedImage convertToBufferedImage(List<Node> leftColumn) {
//        BufferedImage newBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int y = 0; y < height; y++) {
//            Color color = leftColumn.get(y).getColor();
//            newBuffer.setRGB(0, y, color.getRGB());
//
//            for (int y = 0; y < height; y++) {
//                Node leftNode = leftColumn.get(y);
//                List<Node> rowNodes = leftNode.getRowNodes();
//                for (int x = 0; x < rowNodes.size(); x++) {
//                    Color color1 = rowNodes.get(x).getColor();
//                    newBuffer.setRGB(x + 1, y, color1.getRGB());
//                }
//            }
//
//        }
//        return newBuffer;
//    }



    /**
     * Add an element to a collection
     */
    private static <T> List<T> concat(T element, Collection<? extends T> elements) {
        List<T> result = new ArrayList<>();
        result.add(element);
        result.addAll(elements);
        return result;
    }

    /**
     * Method that calculates the lowest energy seam
     * for this energy grid
     */

    public List<Pair> getSeam(List<List<Integer>> grid, int width, int height) {
//        int width = img.width;
//        int height = img.height;
        double[] previousValues = new double[width];
        double[] currentValues = new double[width];
        List<List<Pair>> previousSeams = new ArrayList<>();
        List<List<Pair>> currentSeams = new ArrayList<>();
        Pair currentPixel = new Pair(0, 0);

        int col = 0;

        // initializing for first row
        while (col < width) {
            previousValues[col] = grid.get(0).get(col);

            // one seam per column
            previousSeams.add(concat(currentPixel, List.of()));
            col++;
            currentPixel = new Pair(currentPixel.row, currentPixel.col + 1);
        }

        // compute values and paths for each row
        for (int row = 1; row < height; row++) {
            col = 0;
            currentPixel = new Pair(row, col);
            while (col < width) {
                double bestSoFar = previousValues[col];
                int ref = col;
                // check both adjacent pixels
                // if left exists and is better, update
                if (col > 0 && previousValues[col - 1] < bestSoFar) {
                    bestSoFar = previousValues[col - 1];
                    ref = col - 1;
                }
                // if right exists and is better, update
                if (col < width - 1 && previousValues[col + 1] < bestSoFar) {
                    bestSoFar = previousValues[col + 1];
                    ref = col + 1;
                }

                // update the value with the current pixel
                currentValues[col] = bestSoFar + grid.get(currentPixel.row).get(currentPixel.col);

                // append this new pixel to existing seams
                currentSeams.add(concat(currentPixel, previousSeams.get(ref)));

                col++;
                // move to neighbor
                currentPixel = new Pair(currentPixel.row, currentPixel.col + 1);
            }

            // update previous values/seams
            // and reset current values/seams
            previousValues = currentValues;
            currentValues = new double[width];
            previousSeams = currentSeams;
            currentSeams = new ArrayList<>();
        }

        // find the seam with the min sum
        double minValue = previousValues[0];
        int minIndex = 0;
        for (int i = 1; i <width; i++) {
            if (previousValues[i] < minValue) {
                minIndex = i;
                minValue = previousValues[i];
            }
        }
        return previousSeams.get(minIndex);
    }


    //List<Pair> VariableName = gridName.getSeam();
    public void highLightBlue(List<Node> leftColumn){
        List<Pair> coords = getSeam(Image.blueGrid(img.getBufferedImage()));
        for (Pair coord : coords) {
            int x = coord.getCol();
            int y = coord.getRow();

            if (y >= 0 && y < leftColumn.size()) {
                Node node = leftColumn.get(y);
                List<Node> rowNodes = node.getRowNodes();
                if (x >= 0 && x < rowNodes.size()) {
                    Node targetNode = rowNodes.get(x);
                    targetNode.setColor(Color.BLUE);
                }
            }
        }
    }

    public void highlightRed(List<Node> leftColumn) {
        //getSeam(Image.blueGrid(img.getBufferedImage()));
        List<Pair> coords = getSeam(Image.energyGrid(img.getBufferedImage()));
        for (Pair coord : coords) {
            int x = coord.getCol();
            int y = coord.getRow();

            if (y >= 0 && y < leftColumn.size()) {
                Node node = leftColumn.get(y);
                List<Node> rowNodes = node.getRowNodes();
                if (x >= 0 && x < rowNodes.size()) {
                    Node targetNode = rowNodes.get(x);
                    targetNode.setColor(Color.RED);
                }
            }
        }
    }
}







