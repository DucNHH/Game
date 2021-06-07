package traditionalgame;

public class Board{
    int[][] bText;
    int[] count;
    int x, y;
    boolean player;
    int eatScore1, eatScore2;

    public Board() {
        this.bText = new int[2][6];
        this.count = new int[2];
        this.player = true;
        this.eatScore1 = 0;
        this.eatScore2 = 0;
        for(int i = 0; i < 5; ++i) {
            this.bText[0][i] = 5;
            this.bText[1][i] = 5;
        }
        this.bText[0][5] = 10;
        this.bText[1][5] = 10;
    }
    
    public Board clone() {
        Board tmp = new Board();
        tmp.bText = this.bText.clone();
        for(int i = 0; i < 2; ++i) {
            tmp.bText[i] = this.bText[i].clone();
        }
        tmp.count = this.count.clone();
        tmp.player = this.player;
        tmp.eatScore1 = this.eatScore1;
        tmp.eatScore2 = this.eatScore2;
        tmp.x = this.x;
        tmp.y = this.y;
        return tmp;
    }
    
    public void move1() {
        int i = this.x;
        int j = this.y;
        do{
            int tmp = this.bText[i][j];
            this.bText[i][j] = 0;
            while(tmp > 0) {
                while(tmp > 0 && j > 0) {
                    --j;
                    ++this.bText[i][j];
                    --tmp;
                }
                if(tmp > 0) {
                    j = 5;
                    i = 1 - i;
                    ++this.bText[i][j];
                    ++this.count[i];
                    --tmp;
                }
            }
            if(j == 0) break;
            --j;
        } while(this.bText[i][j] != 0);
        while(this.bText[i][j] == 0 && j != 5) {
            if(j == 0) {
                if(this.bText[1-i][5] != 0) {
                    if(this.count[1-i] < 4) break;
                    i = 1 - i;
                    j = 6;
                    this.count[i] = 0;
                }
                else break;
            }
            --j;
            if(this.bText[i][j] == 0) break;
            if(this.player) this.eatScore1 += this.bText[i][j];
            else this.eatScore2 += this.bText[i][j];
            this.bText[i][j] = 0;
            if(j > 0) --j;
            else break;
        }
    }
    
    public void move2() {
        int i = this.x;
        int j = this.y;
        do{
            int tmp = this.bText[i][j];
            this.bText[i][j] = 0;
            while(tmp > 0) {
                while(tmp > 0 && j < 4) {
                    ++j;
                    ++this.bText[i][j];
                    --tmp;
                }
                if(tmp > 0) {
                    ++this.bText[i][5];
                    ++this.count[i];
                    j = -1;
                    i = 1 - i;
                    --tmp;
                }
            }
            if(j == 4) break;
            ++j;
        } while(this.bText[i][j] != 0);
        while(this.bText[i][j] == 0 && j != 5 && this.bText[i][++j] != 0) {
            int tmp = this.bText[i][j];
            this.bText[i][j] = 0;
            if(j == 5) {
                if(this.count[i] < 4) {
                    this.bText[i][j] = tmp;
                    break;
                }
                this.count[i] = 0;
                i = 1 - i;
                j = -1;
            }
            if(this.player) this.eatScore1 += tmp;
            else this.eatScore2 += tmp;
            ++j;
        }
    }
    
    public int update() {
        this.player = !this.player;
        int score1 = this.eatScore1;
        int score2 = this.eatScore2;
        for(int i = 0; i < 5; ++i) {
            score1 += this.bText[0][i];
            score2 += this.bText[1][i];
        }
        if(score1 == 0 && this.player) return 2;
        if(score2 == 0 && !this.player) return 1;
        if(this.bText[0][5] == 0 && this.bText[1][5] == 0) {
            if(score1 > score2) return 1;
            if(score2 > score1) return 2;
            return 0;
        }
        if(this.player) {
            int i = 0;
            while(i < 5 && this.bText[0][i] == 0) {
                ++i;
            }
            if(i == 5) {
                while(this.eatScore1 > 0 && i > 0) {
                    --i;
                    this.bText[0][i] = 1;
                    --this.eatScore1;
                }
            }
        }
        else {
            int i = 0;
            while(i < 5 && this.bText[1][i] == 0) {
                ++i;
            }
            if(i == 5) {
                while(this.eatScore2 > 0 && i > 0) {
                    --i;
                    this.bText[1][i] = 1;
                    --this.eatScore2;
                }
            }            
        }
        return -1;
    }
    
    public void moveLeft() {
        int i = this.x;
        int j = this.y;
        if(this.player) this.move1();
        else this.move2();
    }
    
    public void moveRight() {
        int i = this.x;
        int j = this.y;
        if(!this.player) this.move1();
        else this.move2();
    }
}
