package traditionalgame;

import java.util.*;
import javafx.scene.control.Button;

public class DoubleLinkedList {
    public class Nodes {
        Button button;
        Nodes next;
        Nodes pre;

        public Nodes(Button button) {
            this.button = button;
            this.next = null;
            this.pre = null;
        }
    }
    Nodes head, last;
    void add(Button b) {
        Nodes tmp = new Nodes(b);
        if(head == null) {            
            head = tmp;
            head.pre = last;
            last = tmp;       
            last.next = head;
        }
        else {
            last.next = tmp;
            tmp.pre = last;
            tmp.next = head;
            head.pre = tmp;
            last = tmp;
        }
    }
    
    void add(Button[][] arr) {
        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr[i].length; ++j) {
                add(arr[i][j]);
            }
        }
    }
    
}
