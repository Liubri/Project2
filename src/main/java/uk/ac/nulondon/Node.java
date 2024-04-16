package uk.ac.nulondon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Node {
    public String data;
    Node head;
    Node next;
    Node previous;


    int x;
    int y;
//    int red;
//    int green;
//    int blue;

    private List<Node> neighbors;

    public Node(int x, int y, Color rgb) {
        this.next = null;
        this.neighbors = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public void addNode(int x, int y, Color rgb) {
        Node newNode = new Node(x, y, rgb);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.previous = current;
        }
    }

    public void setNextNode(Node node) {
        this.next = node;
    }

    public Node getNextNode() {
        return this.next;
    }


//    public int getColor() {
//        // Create a Color object with the RGB values of the node
//        Color color = new Color(red, green, blue);
//        // Get the RGB components from the Color object and calculate their sum
//        return color.getRed() + color.getGreen() + color.getBlue();
//    }

//    public int calculateAvgRGBvalue(){
//        return getColor()/3;
//    }
//}
