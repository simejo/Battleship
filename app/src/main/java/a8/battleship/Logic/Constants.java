package a8.battleship.Logic;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Constants {
    private int boardSize = 10;
    private int screenSize;
//I think we should make the variables static, in stead of making getters and setters for all of them
    public int getBoardSize(){
        return boardSize;
    }

    public void setBoardSize(int n){
        boardSize=n;
    }
}
