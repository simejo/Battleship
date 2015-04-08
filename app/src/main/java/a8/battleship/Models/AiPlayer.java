package a8.battleship.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by TheaHove on 11/03/2015.
 */
public class AiPlayer {
    //TODO: Implement AI
    private Board board;
    private boolean turn;
    private ArrayList<Integer> rndPos;

    public AiPlayer() {
        this.board = null;
        this.turn = true;

        rndPos = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            rndPos.add(i);
        }
        Collections.shuffle(rndPos);
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

    public Board getBoard(){
        return this.board;
    }

    public Integer aiPositions() {
        return rndPos.remove(0);
    }
}
