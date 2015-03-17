package a8.battleship.Models;

/**
 * Created by TheaHove on 11/03/2015.
 */
public class Player {

    private String name;
    private Board board;
    private boolean turn;

    public Player(String name){
        this.name = name;
        this.board = null;
        this.turn = true;
    }

    public String getName(){
        return name;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void setTurn(boolean turn){
        this.turn = turn;
    }

    public boolean getTurn(){
        return turn;
    }


}
