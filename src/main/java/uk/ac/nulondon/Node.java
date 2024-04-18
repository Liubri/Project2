package uk.ac.nulondon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Node {
    public String data;
    static Node head;
    static  Node next;
    static  Node previous;

    int x;
    int y;



    static Color rgb;

    static Color color;

    public Node(int x, int y, Color rgb) {
        this.next = null;
        this.rgb = rgb;

        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return rgb;
    }



    public void setColor(Color color) {
        this.color = color;
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

    public int getDSWidth() {
        int count = 0;
        Node current = Node.head;
        while (current.next!=null) {
            count++;
            current = current.next;
        }
        return count;
    }
}


