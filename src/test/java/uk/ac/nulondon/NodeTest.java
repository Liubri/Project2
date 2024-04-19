package uk.ac.nulondon;


import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;


import java.awt.Color;

public class NodeTest {

    @Test
    public void DSwidthTest(){
        Node start = new Node(0,0, Color.red);
        start.addNode(1,0,Color.GRAY);
        start.next.addNode(2,0,Color.GRAY);

        int width = start.getDSWidth();
        Assertions.assertThat(width).isEqualTo(3);
    }

    @Test
    public void testAddNode() {
        // Create a new node as the head
        Node current = new Node(0, 0, Color.RED);

        // Add two nodes to the linked list
        current.addNode(1, 0, Color.BLUE);
        current.next.addNode(2, 0, Color.GREEN);

        Assertions.assertThat(current.x).isEqualTo(0);
        Assertions.assertThat(current.next.x).isEqualTo(1);
        Assertions.assertThat(current.next.rgb).isEqualTo(Color.BLUE);
        Assertions.assertThat(current.next.next.x).isEqualTo(2);
        Assertions.assertThat(current.next.next.rgb).isEqualTo(Color.GREEN);

    }

}
