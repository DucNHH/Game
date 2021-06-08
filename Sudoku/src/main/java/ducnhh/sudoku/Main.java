package ducnhh.sudoku;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("D:\\Sudoku.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        int x = 0, y = 0;
        Board board = new Board();
        while(bis.available() > 0) {
            char c = (char) bis.read();
            if(c >= '0' && c <= '9') {
                board.stat[x][y].setData(c - '0');
                ++y;
            }
            if(c == '\n') {
                ++x;
                y = 0;
            }
        }
        board.solve();
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                System.out.print(board.stat[i][j].getData() + " ");
            }
            System.out.println("");
        }
    }
}

