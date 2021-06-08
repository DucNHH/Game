package ducnhh.sudoku;

public class Board {
    Box[][] stat;

    public Board() {
        this.stat = new Box[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; j++) {
                this.stat[i][j] = new Box();
            }
        }
    }

    public void solve() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; j++) {
                if (this.stat[i][j].getData() != 0) update(i, j);
            }
        }
        boolean check = false;
        do {
            check = false;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (this.stat[i][j].getData() != 0) continue;
                    if (checkBox(i, j)) {
                        update(i, j);
                        check = true;
                    } else if (checkRow(i, j)) {
                        update(i, j);
                        check = true;
                    } else if (checkCol(i, j)) {
                        update(i, j);
                        check = true;
                    } else if (checkArea(i, j)) {
                        update(i, j);
                        check = true;
                    }
                }
            }
        } while (check);
    }

    public void update(int x, int y) {
        int number = this.stat[x][y].getData();
        for (int i = 0; i < 9; ++i) {
            if (this.stat[x][i].getData() == 0) {
                this.stat[x][i].getTick()[number] = true;
            }
            if (this.stat[i][y].getData() == 0) {
                this.stat[i][y].getTick()[number] = true;
            }
        }
        for (int i = 0; i < 3; ++i) {
            int m = x / 3 * 3 + i;
            for (int j = 0; j < 3; ++j) {
                int n = y / 3 * 3 + j;
                if (this.stat[m][n].getData() == 0) {
                    this.stat[m][n].getTick()[number] = true;
                }
            }
        }
    }

    public boolean checkBox(int x, int y) {
        if (this.stat[x][y].check() == 8) {
            for (int i = 1; i <= 9; ++i) {
                if (!this.stat[x][y].getTick()[i]) {
                    this.stat[x][y].setData(i);
                    System.out.println(x + " " + y + " " + i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkRow(int x, int y) {
        for (int number = 1; number <= 9; ++number) {
            if (this.stat[x][y].getTick()[number]) continue;
            boolean check = true;
            for (int i = 0; i < 9; ++i) {
                if (y != i && !this.stat[x][i].getTick()[number]) {
                    check = false;
                    break;
                }
            }
            if (check) {
                this.stat[x][y].setData(number);
                System.out.println(x + " " + y + " " + number);
                return true;
            }
        }
        return false;
    }

    public boolean checkCol(int x, int y) {
        for (int number = 1; number <= 9; ++number) {
            if (this.stat[x][y].getTick()[number]) continue;
            boolean check = true;
            for (int i = 0; i < 9; ++i) {
                if (x != i && !this.stat[i][y].getTick()[number]) {
                    check = false;
                    break;
                }
            }
            if (check) {
                this.stat[x][y].setData(number);
                System.out.println(x + " " + y + " " + number);
                return true;
            }
        }
        return false;
    }

    public boolean checkArea(int x, int y) {
        for (int number = 1; number <= 9; ++number) {
            if (this.stat[x][y].getTick()[number]) continue;
            boolean check = true;
            for (int i = 0; i < 3; ++i) {
                int m = x / 3 * 3 + i;
                for (int j = 0; j < 3; ++j) {
                    int n = y / 3 * 3 + j;
                    if ((x != m || y != n) && !this.stat[m][n].getTick()[number]) {
                        check = false;
                        break;
                    }
                }
            }
            if (check) {
                this.stat[x][y].setData(number);
                System.out.println(x + " " + y + " " + number);
                return true;
            }
        }
        return false;
    }
}
