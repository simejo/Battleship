package a8.battleship.Models;

import android.util.Log;

import java.util.ArrayList;

import a8.battleship.Logic.BoardValues;

/**
 * Created by TheaHove on 11/03/2015.
 * A player has a name (might be null), a board, and a boolean that tells if it's his/hers turn or not
 */
public class Player {

    private String name;
    private Board board;
    private boolean turn;

    public  Player() {
        this.board = null;
        this.turn = true;
    }

    public Player(String name){
        this.name = name;
        this.board = null;
        this.turn = true;
        Log.i("Player.class", "Player created with name: " + name);
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

    public String toString(){
        return name;
    }

    public Board getBoard(){
        return this.board;
    }



}
