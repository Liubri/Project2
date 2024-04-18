package uk.ac.nulondon;


import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.awt.*;

public class NodeTest {

    @Test
    public void DSwidthTest(){
        Node start = new Node(0,1, Color.red);
        start.next = new Node(1,0,Color.BLUE);
        start.next.next = new Node(2,0,Color.BLACK);
        Assertions()
    }

}
