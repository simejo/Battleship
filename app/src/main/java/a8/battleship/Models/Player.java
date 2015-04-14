package a8.battleship.Models;

import android.util.Log;

/**
 * A player has a name (might be null), a board, and a boolean that tells if it's his/hers turn or not
 */
public class Player {

    private String name;
    private Board board;
    private int score;

    public  Player() {
        this.name = "";
        this.board = null;
        this.score = 0;
    }

    public Player(String name){
        this.name = name;
        this.board = null;
        this.score = 0;
        Log.i("Player.class", "Player created with name: " + name);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void incrementScore(){
        this.score += 10;
    }

    public void decrementScore(){
        this.score -= 1;
    }

    public String toString(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public Board getBoard(){
        return this.board;
    }



}
