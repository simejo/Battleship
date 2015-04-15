package a8.battleship.Models;

import android.util.Log;

import java.util.ArrayList;

import a8.battleship.Logic.BoardValues;

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

    /**
     * A constructor when name fields are filled in
     * @param name When the player gets a name
     */
    public Player(String name){
        this.name = name;
        this.board = null;
        this.score = 0;
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

    //A setter method to increase the score of the player
    public void incrementScore(){
        this.score += 10;
    }

    //A setter method to decrease the score of the player
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
