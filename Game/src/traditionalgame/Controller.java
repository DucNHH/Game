package traditionalgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import traditionalgame.DoubleLinkedList.Nodes;


public class Controller{
    
    private int eatScore1 = 0, eatScore2 = 0, score1, score2;
    private boolean check = true;
    private int count1, count2;
    @FXML private Text player1, player2;
    @FXML private Button bLeft, bRight;
    Background b = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
    Background d = new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY));
    Button tmp;
    @FXML Button b_0_0, b_0_1, b_0_2, b_0_3, b_0_4, b_0_5, b_1_0, b_1_1, b_1_2, b_1_3, b_1_4, b_1_5;
    Button[][] arr;
    DoubleLinkedList list = new DoubleLinkedList();
    String[] tmpList;
    
    public void start(ActionEvent event) {
        arr = new Button[][]{{b_0_0, b_0_1, b_0_2, b_0_3, b_0_4}, {b_0_5}, {b_1_0, b_1_1, b_1_2, b_1_3, b_1_4}, {b_1_5}};
        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr[i].length; ++j) {
                arr[i][j].setBackground(d);
            }
        }
        for (int i = 0; i < 5; i++) {
            arr[2][i].setDisable(true);
        }
        list.add(arr);
    }
    
    public void reStart(ActionEvent event) {
        eatScore1 = 0;
        eatScore2 = 0;
        count1 = 0;
        count2 = 0;
        for(int i = 0; i < 5; ++i) {
            arr[0][i].setText("5");
            arr[0][i].setDisable(false);
        }
        for(int i = 0; i < 5; ++i) {
            arr[2][i].setText("5");
            arr[2][i].setDisable(true);
        }
        check = true;
        b_0_5.setText("10");
        b_1_5.setText("10");
        player1.setText(String.valueOf(eatScore1));
        player2.setText(String.valueOf(eatScore2));
    }
    
    public void ready(ActionEvent event) {
        if(tmp != null) tmp.setBackground(d);
        tmp = (Button) event.getSource();
        tmp.setBackground(b);
        if(!tmp.getText().equals("0")) {
            bLeft.setDisable(false);
            bRight.setDisable(false);
        }
    }
    
    public void play() {
        int maxScore = 0;
        tmpList = new String[12];
        int index = 0;
        int a = 0;
        boolean tick = true;
        int tmpScore = eatScore2; 
        int tmpCount1 = count1;
        int tmpCount2 = count2;
        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr[i].length; ++j) {
                tmpList[index] = arr[i][j].getText();
                ++index;
            }
        }
        for(int i = 0; i < 5; ++i) {
            tmp = arr[2][i];
            if(tmp.getText().equals("0")) {
                if(maxScore == 0) ++a;
                continue;
            }
            moveLeft();
            if(eatScore2 > maxScore) {
                maxScore = eatScore2;
                a = i;
                tick = true;
            }
            count1 = tmpCount1;
            count2 = tmpCount2;
            index = 0;
            for(int j = 0; j < arr.length; ++j) {
                for(int k = 0; k < arr[j].length; ++k) {
                    arr[j][k].setText(tmpList[index]);
                    ++index;
                }
            }
            index = 0;
            eatScore2 = tmpScore;
            moveRight();
            if(eatScore2 > maxScore) {
                maxScore = eatScore2;
                a = i;
                tick = false;
            }
            count1 = tmpCount1;
            count2 = tmpCount2;
            eatScore2 = tmpScore;
            for(int j = 0; j < arr.length; ++j) {
                for(int k = 0; k < arr[j].length; ++k) {
                    arr[j][k].setText(tmpList[index]);
                    ++index;
                }
            }
        }
        tmp = arr[2][a];
        move(tick);
        System.out.println(a + " " + tick);
    }
    
    public void update() {
        check = !check;
        for(int i = 0; i < 5; ++i) {
            arr[2][i].setDisable(check);
            arr[0][i].setDisable(!check);
        }
        score1 = eatScore1;
        for(int i = 0; i < 5; ++i) {
            score1 += Integer.parseInt(arr[0][i].getText());
        }
        score2 = eatScore2;
        for(int i = 0; i < 5; ++i) {
            score2 += Integer.parseInt(arr[2][i].getText());
        }
        if(score1 == 0 && check) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("PLAYER 2 WIN");
        }
        else if(score2 == 0 && !check) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("PLAYER 1 WIN");
        }
        else if(b_0_5.getText().equals("0") && b_1_5.getText().equals("0")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(score1 > score2) {
                alert.setContentText("PLAYER 1 WIN");
            }
            else if(score1 <  score2) {
                alert.setContentText("PLAYER 2 WIN");
            }
            else alert.setContentText("YOU TWO DRAW");
            alert.show();
        }
        else {
            if(!check) {
                boolean tick = true;
                for(int i = 0; i < 5; ++i) {
                    if(!arr[2][i].getText().equals("0")) {
                        tick = false;
                        break;
                    }
                }
                if(tick) {
                    for(int i = 0; i < 5 && eatScore1 > 0; ++i) {
                        arr[2][i].setText("1");
                        --score2;
                        --eatScore2;
                    }
                }
            }
            else {
                boolean tick = true;
                for(int i = 0; i < 5; ++i) {
                    if(!arr[0][i].getText().equals("0")) {
                        tick = false;
                        break;
                    }
                }
                if(tick) {
                    for(int i = 0; i < 5 && eatScore1 > 0; ++i) {
                        arr[0][i].setText("1");
                        --score1;
                        --eatScore1;
                    }
                }
            }
        }
        player1.setText(String.valueOf(eatScore1));
        player2.setText(String.valueOf(eatScore2));
        if(!check) play();
    }
    
    public void moveLeft() {
        Nodes a = list.head;
        while(tmp != a.button) a = a.next;
        do{
            String s = a.button.getText();
            int step = Integer.parseInt(s);
            a.button.setText("0");
            for(int i = 0; i < step; ++i) {
                a = a.pre;
                if(a.button == b_0_5) ++count1;
                else if(a.button == b_1_5) ++count2;
                Button k = a.button;
                int x = Integer.parseInt(k.getText());
                k.setText(String.valueOf(++x));
            }
            a = a.pre;
        }while(a.button != b_0_5 && a.button != b_1_5 && !a.button.getText().equals("0"));
        while(a.button.getText().equals("0") && !a.pre.button.getText().equals("0")) {
            if(a.button == b_0_5 || a.button == b_1_5) break;
            a = a.pre;
            if(a.button == b_0_5) {
                if(count1 < 4) break;
                count1 = 0;
            }
            if(a.button == b_1_5) {
                if(count2 < 4) break;
                count2 = 0;
            }
            if(check) eatScore1 += Integer.parseInt(a.button.getText());
            else eatScore2 += Integer.parseInt(a.button.getText());
            a.button.setText("0");
            a = a.pre;
        }
        bLeft.setDisable(true);
        bRight.setDisable(true);
        tmp.setBackground(d);
    }
    
    public void moveRight() {
        Nodes a = list.head;
        while(tmp != a.button) a = a.next;
        do{
            String s = a.button.getText();
            int step = Integer.parseInt(s);
            a.button.setText("0");
            for(int i = 0; i < step; ++i) {
                a = a.next;
                if(a.button == b_0_5) ++count1;
                else if(a.button == b_1_5) ++count2;
                Button k = a.button;
                int x = Integer.parseInt(k.getText());
                k.setText(String.valueOf(++x));
            }
            a = a.next;
        }while(a.button != b_0_5 && a.button != b_1_5 && !a.button.getText().equals("0"));
        while(a.button.getText().equals("0") && !a.next.button.getText().equals("0")) {
            if(a.button == b_0_5 || a.button == b_1_5) break;
            a = a.next;
            if(a.button == b_0_5) {
                if(count1 < 4) break;
                count1 = 0;
            }
            if(a.button == b_1_5) {
                if(count2 < 4) break;
                count2 = 0;
            }
            if(check) eatScore1 += Integer.parseInt(a.button.getText());
            else eatScore2 += Integer.parseInt(a.button.getText());
            a.button.setText("0");
            a = a.next;
        }
        bLeft.setDisable(true);
        bRight.setDisable(true);
        tmp.setBackground(d);
    }
    
    public void move1(ActionEvent event) {
        if(check) moveLeft();
        else moveRight();
        update();
    }
    public void move2(ActionEvent event) {
        if(!check) moveLeft();
        else moveRight();
        update();
    }
    
    public void move(boolean c) {
        if(c) moveLeft();
        else moveRight();
        update();
    }
}
