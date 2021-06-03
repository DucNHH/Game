package ducnhh.sudoku;

import java.util.HashSet;

public class Box {
    private int data = 0;
    private HashSet<Integer> nca;
    private boolean[] tick;
    
    public Box() {
        this.nca = new HashSet<>();
        this.tick = new boolean[10];
    }
}
