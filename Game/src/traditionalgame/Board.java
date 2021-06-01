package traditionalgame;

public class Board {
    int[][] bText;
    int[] count = new int[2];
    boolean player;
    int eatScore1, eatScore2;
    
    
    
    public void moveLeft(int i, int j) {
        do{
            int tmp = bText[i][j];
            bText[i][j] = 0;
            while(tmp > 0) {
                while(tmp > 0 && j > 0) {
                    --j;
                    ++bText[i][j];
                    --tmp;
                }
                if(tmp > 0) {
                    j = 5;
                    i = 1 - i;
                    ++bText[i][j];
                    ++count[i];
                    --tmp;
                }
            }
            if(j == 0) break;
            --j;
        } while(bText[i][j] != 0);
        while(bText[i][j] == 0 && j != 5) {
            if(j == 0) {
                if(bText[1-i][5] != 0) {
                    if(count[i] < 4) break;
                    i = 1 - i;
                    j = 6;
                    count[i] = 0;
                }
            }
            --j;
            if(player) eatScore1 += bText[i][j];
            else eatScore2 += bText[i][j];
            if(j > 0) --j;
            else break;
        }
    }
    
    public void moveRight(int i, int j) {
        do{
            int tmp = bText[i][j];
            bText[i][j] = 0;
            while(tmp > 0) {
                while(tmp > 0 && j < 4) {
                    ++j;
                    ++bText[i][j];
                    --tmp;
                }
                if(tmp > 0) {
                    ++bText[i][5];
                    ++count[i];
                    j = -1;
                    i = 1 - i;
                    --tmp;
                }
            }
            if(j == 4) break;
            ++j;
        } while(bText[i][j] != 0);
        while(bText[i][j] == 0 && j != 5 && bText[i][++j] != 0) {
            ++j;
            int tmp = bText[i][j];
            if(j == 5) {
                if(count[i] < 4) break;
                count[i] = 0;
                i = 1 - i;
                j = -1;
            }
            if(player) eatScore1 += tmp;
            else eatScore2 += tmp;
            ++j;
        }
    }
}
