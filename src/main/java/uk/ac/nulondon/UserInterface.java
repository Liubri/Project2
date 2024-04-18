package uk.ac.nulondon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserInterface {
    static Scanner scan = new Scanner(System.in);
    static EditingImage editor;
    static Image img;

    static Image originalImgCopy;

    /**
     * asks user to enter in the file path
     */
    public static void filePath() {
        System.out.println("Please enter the file path:");
    }

    /**
     * prints out the options
     */
    public static void printMenu() {
        System.out.println("Please make a selection!");
        System.out.println("1.) press 'b' to show the bluest seam");
        System.out.println("2.) press 'r' to show the lowest energy seam");
        System.out.println("3.) press 'd' to delete seam from image");
        System.out.println("3.) press 'u' to undo the most recent deletion");
        System.out.println("4.) press 'q' to quit");
    }


    /**
     * asks user for confirmation and takes in their input
     */
    public static void confirmation() {
        System.out.println("Proceed with removal. Y/N?");

        boolean shouldQuitConfirm = false;

        try {
            confirm = scan.next();

        } catch (InputMismatchException e) {
            System.out.println("Not valid input");
        }

    }


    /**
     * based on user's selection, will give different results
     * @param selection
     */
    public static void printResponse(String selection) {
        switch (selection) {
            case "b":
                System.out.println("Your choice is b!");
                editor.highlightBlue(img.getGrid());
                confirmation();
                if (confirm.toLowerCase().equals("y")) {
                    img.deleteColumn(img.getBluestColumn(img.getGrid()), img.getGrid());
                } else {
                    editor.undoPrevEdit(false);
                }
                break;
            case "r":
                System.out.println("Your choice is r!");
                editor.highlightRed(img.getGrid());
                confirmation();

                if (confirm.toLowerCase().equals("y")) {
                    img.deleteColumn(editor.randomColumn, img.getGrid());
                } else {
                    editor.undoPrevEdit(false);
                }
                break;

            case "d":

            case "u":
                System.out.println("Your choice is u!.");
                editor.undoPrevEdit(true);
                editor.undoPrevEdit(true);
                break;
            case "q":
                System.out.println("You chose to quit.");
                isFinalVersion = true;
                break;
        }
    }

    public static String filePath = "";
    public static String choice = "";
    public static String confirm = "";
    static boolean isFinalVersion = false;

    /**
     * uses scanner and takes in user's input
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        boolean shouldQuit = false;
        filePath();

        try {
            filePath = scan.next();
            editor = new EditingImage(filePath);
            img = editor.getImage();

        } catch (InputMismatchException e) {
            System.out.println("Didn't enter anything");

        }

        while(!shouldQuit) {
            printMenu();

            try {
                choice = scan.next();

            } catch (InputMismatchException e) {
                System.out.println("Input should be a letter.");

            }
            printResponse(choice);

            // if choice is quit, exit the while-loop
            if(choice.equals("q")) {
                shouldQuit = true;
            }
        }
    }
}

}
//        static Scanner scan = new Scanner(System.in);
//        static EditingImage editor;
//        static Node img;
//
//        static Node originalImgCopy;
//
//        /**
//         * asks user to enter in the file path
//         */
//        public static void filePath() {
//            System.out.println("Please enter the file path:");
//        }
//
//        /**
//         * prints out the options
//         */
//        public static void printMenu() {
//            System.out.println("Please make a selection!");
//            System.out.println("1.) press 'b' to remove the bluest column");
//            System.out.println("2.) press 'r' to remove a randomly selected column");
//            System.out.println("3.) press 'u' to undo the most recent deletion");
//            System.out.println("4.) press 'q' to quit");
//        }
//
////    public static void printFileResponse(String selection) {
////        if (selection.equals("a")) {
////            System.out.println("Please enter a valid file path");
////        }
////    }
//
//        /**
//         * asks user for confirmation and takes in their input
//         */
//        public static void confirmation() {
//            System.out.println("Proceed with removal. Y/N?");
//
//            boolean shouldQuitConfirm = false;
//
//            try {
//                confirm = scan.next();
//
//            } catch (InputMismatchException e) {
//                System.out.println("Not valid input");
//            }
//
//        }
//
//
//        /**
//         * based on user's selection, will give different results
//         * @param selection
//         */
//        public static void printResponse(String selection) {
//            switch (selection) {
//                case "b":
//                    System.out.println("Your choice is b!");
//                    editor.highlightBlue(img.getGrid());
//                    confirmation();
//                    if (confirm.toLowerCase().equals("y")) {
//                        img.deleteColumn(img.getBluestColumn(img.getGrid()), img.getGrid());
//                    } else {
//                        editor.undoPrevEdit(false);
//                    }
//                    break;
//                case "r":
//                    System.out.println("Your choice is r!");
//                    editor.highlightRed(img.getGrid());
//                    confirmation();
//
//                    if (confirm.toLowerCase().equals("y")) {
//                        img.deleteColumn(editor.randomColumn, img.getGrid());
//                    } else {
//                        editor.undoPrevEdit(false);
//                    }
//                    break;
//                case "u":
//                    System.out.println("Your choice is u!.");
//                    editor.undoPrevEdit(true);
//                    editor.undoPrevEdit(true);
//                    break;
//                case "q":
//                    System.out.println("You chose to quit.");
//                    isFinalVersion = true;
//                    break;
//            }
//        }
//
//        public static String filePath = "";
//        public static String choice = "";
//        public static String confirm = "";
//        static boolean isFinalVersion = false;
//
//        /**
//         * uses scanner and takes in user's input
//         * @param args
//         * @throws IOException
//         */
//
//        public static void main(String[] args) throws IOException {
//
//
//
//            // Load the PNG image
//            BufferedImage image = ImageIO.read(new File());
//
//            boolean shouldQuit = false;
//            filePath();
//
//            try {
//                filePath = scan.next();
//                editor = new EditingImage(filePath);
//                img = editor.getImage();
//
//            } catch (InputMismatchException e) {
//                System.out.println("Didn't enter anything");
//
//            }
//
//            while(!shouldQuit) {
//                printMenu();
//
//                try {
//                    choice = scan.next();
//
//                } catch (InputMismatchException e) {
//                    System.out.println("Input should be a letter.");
//
//                }
//                printResponse(choice);
//
//                // if choice is quit, exit the while-loop
//                if(choice.equals("q")) {
//                    shouldQuit = true;
//                }
//            }
//        }
//    }
//
//}
