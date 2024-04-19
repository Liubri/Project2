package uk.ac.nulondon;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Node {
    public String data;
    public Node head;
    Node next;
    Node previous;

    int x;
    int y;

    Color rgb;

    public Node(int x, int y, Color rgb) {
        this.next = null;
        this.rgb = rgb;
//        this.previous = previous;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return rgb;
    }



    public void setColor(Color color) {
        this.rgb = color;
    }


     public void addNode(int x, int y, Color rgb) {
         Node newNode = new Node(x, y, rgb);
         Node current = this;
         while (current.next != null) {
             current = current.next;
         }
         current.next = newNode;
         newNode.previous = current;
//            Node newNode = new Node(x, y, rgb);
//            this.next = newNode;
//            newNode.previous = this;
//            if (head == null) {
//                head = newNode;
//                return;
//            } else {
//                Node current = head;
//                while (current.next != null) {
//                    current = current.next;
//                }
//                current.next = newNode;
//                newNode.previous = current;
//            }
    }



    public void setNextNode(Node node) {
        this.next = node;
    }

    public Node getNextNode() {
        return this.next;
    }

    public void setPreviousNode(Node previousNode) {
        this.previous = previous;
    }

    public int getDSWidth() {
        int count = 0;
        Node current = this;
        while (current!=null) {
            count++;
            current = current.next;
        }
        return count;
    }
}


