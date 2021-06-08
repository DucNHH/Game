package ducnhh.sudoku;


public class Box {
    private int data = 0;
    private boolean[] tick;
    
    public Box() {
        this.tick = new boolean[10];
    }

    public void setData(int data) {
        this.data = data;
        if(data != 0) for(int i = 1; i <= 9; ++i) {
            if(i != data) {
                this.tick[i] = true;
            }
        }
    }

    public int getData() {
        return data;
    }

    public boolean[] getTick() {
        return tick;
    }
    
    public int check() {
        int count = 0;
        for(int i = 1; i <= 9; ++i) {
            if(this.tick[i]) ++count;
        }
        return count;
    }
}
