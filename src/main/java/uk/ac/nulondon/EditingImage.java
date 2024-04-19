package uk.ac.nulondon;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class EditingImage {
    Image img;
    private ArrayList<ArrayList<Integer>> grid;
    static ArrayList<ArrayList<Pair>> previousSeams;

    static ArrayList<ArrayList<Pair>> currentSeams;

    static double[] previousValues;

    double[] currentValues;

    static int counter = 0;

    public EditingImage(Image img) {
        this.img = img;
    }

    public EditingImage(ArrayList<ArrayList<Integer>> grid) {
        this.grid = grid;
    }

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
     *
     * @return
     */
    public int counter() {
        counter++;
        return counter;
    }

    /**
     * Saves the new image after every change
     * //     * @param newBuffer
     * //     * @param counter
     */
//    public void saveNewImage(BufferedImage newBuffer, int counter, boolean finalVersion) {
//        try {
//            if (!finalVersion) {
//                // Write the BufferedImage to a PNG file
//                File f = new File("src/main/java/uk/ac/nulondon/tempImg" + counter + ".png");
//                ImageIO.write(newBuffer, "png", f);
//                System.out.println("Image saved successfully");
//            }
//
//            else {
//                File f = new File("src/main/java/uk/ac/nulondon/newImg" + ".png");
//                ImageIO.write(newBuffer, "png", f);
//                System.out.println("Image saved successfully");
//            }
//        } catch (IOException e) {
//            System.out.println("Error with file: " + e.getMessage());
//        }
//    }
    public BufferedImage convertToBufferImage(ArrayList<Node> leftColumn) {
        int DSheight = leftColumn.size();
        int DSwidth = leftColumn.get(0).getDSWidth();

        BufferedImage bufferedImage = new BufferedImage(DSwidth, DSheight, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < DSheight; y++) {
            Node current = leftColumn.get(y);
            int x = 0;
//            for(int x = 0; x<DSwidth; x++){
            while (current != null) {
                bufferedImage.setRGB(x, y, current.getColor().getRGB());
                current = current.getNextNode();
                x++;
            }
        }
        return bufferedImage;
    }

    /**
     * Saves the new image after every change
     *
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
            } else {
                File f = new File("src/main/java/uk/ac/nulondon/newImg" + ".png");
                ImageIO.write(newBuffer, "png", f);
                System.out.println("Image saved successfully");
            }
        } catch (IOException e) {
            System.out.println("Error with file: " + e.getMessage());
        }
    }


    /**
     * Add an element to a collection
     */
    private static <T> ArrayList<T> concat(T element, Collection<? extends T> elements) {
        ArrayList<T> result = new ArrayList<>();
        result.add(element);
        result.addAll(elements);
        return result;
    }

    /**
     * Method that calculates the lowest energy seam
     * for this energy grid
     */


    public ArrayList<Pair> getSeam(ArrayList<ArrayList<Integer>> grid, int width, int height) {
        previousValues = new double[width];
        currentValues = new double[width];
        previousSeams = new ArrayList<>();
        currentSeams = new ArrayList<>();

        Pair currentPixel = new Pair(0, 0);

        int col = 0;


        while (col < width) {
            previousValues[col] = grid.get(0).get(col);


            previousSeams.add(concat(currentPixel, new ArrayList<>()));
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
        for (int i = 1; i < width; i++) {
            if (previousValues[i] < minValue) {
                minIndex = i;
                minValue = previousValues[i];
            }
        }
        return previousSeams.get(minIndex);
    }


    public void highLightBlue(ArrayList<Node> leftColumn, ArrayList<Pair> seam) {
//        List<Pair> seam = getSeam(img.blueGrid(img.getBufferedImage()), img.width, img.height);
        for (Pair coord : seam) {

            int x = coord.getCol();
            int y = coord.getRow();
            int iter = 0;

            Node pixel = leftColumn.get(y);
            while (pixel != null && iter < x) {
                pixel = pixel.next;
                iter++;
            }
            if (pixel != null && iter == x) {
                pixel.setColor(Color.BLUE);
            }
        }

//        public void highlightRed (ArrayList < Node > leftColumn) {
//            //getSeam(Image.blueGrid(img.getBufferedImage()));
//            List<Pair> seam = getSeam(img.energyGrid(img.getBufferedImage()), img.width, img.height);
//            for (Pair coord : seam) {
//                int x = coord.getCol();
//                int y = coord.getRow();
//
//                if (y >= 0 && y < leftColumn.size()) {
//                    Node current = leftColumn.get(y);
//                    int index = 0;
//                    while (current != null) {
//                        if (index == x) {
//                            current.setColor(Color.RED);
//                            break;
//                        }
//                        current = current.getNextNode();
//
//
//                        index++;
//                    }
//                }
//            }
//        }
    }

//    public void highlightRed(List<Node> leftColumn) {
//        //getSeam(Image.blueGrid(img.getBufferedImage()));
//        List<Pair> coords = getSeam(Image.energyGrid(img.getBufferedImage()));
//        for (Pair coord : coords) {
//            int x = coord.getCol();
//            int y = coord.getRow();
//
//            if (y >= 0 && y < leftColumn.size()) {
//                Node node = leftColumn.get(y);
//                List<Node> rowNodes = node.getRowNodes();
//                if (x >= 0 && x < rowNodes.size()) {
//                    Node targetNode = rowNodes.get(x);
//                    targetNode.setColor(Color.RED);
//                }
//            }
//        }
//    }


//    public void undoPrevEdit() {
//        // Check if there are edits to undo
//        if (!previousValues.isEmpty() && !previousSeams.isEmpty()) {
//            // Retrieve the previous seam
//            ArrayList<Pair> previousSeam = previousSeams.remove(previousSeams.size() - 1);
//
//            // Iterate over the pixels in the seam and restore their colors
//            for (Pair pixel : previousSeam) {
//                int x = pixel.getCol();
//                int y = pixel.getRow();
//                Color color = new Color(oldImg.getRGB(x, y));
//                oldImg.setRGB(x, y, color.getRGB());
//            }
//        }
//
//
//    }


}


