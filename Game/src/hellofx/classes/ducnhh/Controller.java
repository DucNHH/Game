package ducnhh;

import java.util.Stack;
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

public class Controller {
    @FXML Button b_0_0, b_0_1, b_0_2, b_0_3, b_0_4, b_0_5, b_1_0, b_1_1, b_1_2, b_1_3, b_1_4, b_1_5;
    @FXML private Text player1, player2;
    @FXML private Button bLeft, bRight, change;
    Background pick = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
    Background normal = new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY));
    Button[][] buttons;
    Board board;
    Stack<Board> backup;
    
    public void start() {
        board = new Board();
        backup = new Stack<>();
        buttons = new Button[][]{{b_0_0, b_0_1, b_0_2, b_0_3, b_0_4, b_0_5}, {b_1_0, b_1_1, b_1_2, b_1_3, b_1_4, b_1_5}};
        setBoard();
    }
    
    public void setBoard() {
        for(int i = 0; i < 6; ++i) {
            buttons[0][i].setBackground(normal);
            buttons[0][i].setText(board.bText[0][i] + "");
            buttons[1][i].setBackground(normal);
            buttons[1][i].setText(board.bText[1][i] + "");
        }
        for(int i = 0; i < 5; ++i) {
            buttons[0][i].setDisable(!board.player);
            buttons[1][i].setDisable(board.player);
        }
        change.setText("ONE PLAYER");
        player1.setText(board.eatScore1 + "");
        player2.setText(board.eatScore2 + "");
    }
    
    public void ready(ActionEvent event) {
        Button tmp = (Button)event.getSource();
        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 5; ++j) {
                buttons[i][j].setBackground(normal);
                if(buttons[i][j] == tmp) {
                    board.x = i;
                    board.y = j;
                }
            }
        }
        bLeft.setDisable(false);
        bRight.setDisable(false);
        tmp.setBackground(pick);
    }
    
    public void gameOver(int result) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(result > 0) {
            alert.setContentText("PLAYER " + result + " WIN");
        }
        else alert.setContentText("YOU TWO DRAW");
        alert.show();
    }
    
    public void show(int result) {
        for(int i = 0; i < 5; ++i) {
            buttons[0][i].setDisable(!board.player);
            buttons[1][i].setDisable(board.player);
        }
        for(int i = 0; i < 6; ++i) {
            buttons[0][i].setText(board.bText[0][i] + "");
            buttons[1][i].setText(board.bText[1][i] + "");
        }
        player1.setText(board.eatScore1 + "");
        player2.setText(board.eatScore2 + "");
        if(result != -1) {
            gameOver(result);
        }
    }
    
    public void moveLeft(){
        Board tmp = board.clone();
        backup.push(tmp);
        int result = board.moveLeft();
        bLeft.setDisable(true);
        bRight.setDisable(true);
        buttons[board.x][board.y].setBackground(normal);
        if(board.mode && result < 0) result = board.botPlay();
        show(result);   
    }
    
    public void moveRight() {
        Board tmp = board.clone();
        backup.push(tmp);
        int result = board.moveRight();
        bLeft.setDisable(true);
        bRight.setDisable(true);
        buttons[board.x][board.y].setBackground(normal);
        if(board.mode && result < 0) result = board.botPlay();
        show(result);
    }
    
    public void turn() {
        if(!backup.isEmpty()) board = backup.pop();
        setBoard();
    }
    
    public void changeMode() {
        board.mode = !board.mode;
        if(board.mode) change.setText("ONE PLAYER");
        else change.setText("TWO PLAYER");
    }
}
