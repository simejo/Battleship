package a8.battleship.Models;

import java.util.ArrayList;

import a8.battleship.Logic.BoardValues;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Board {

    private ArrayList<ArrayList<BoardValues>> board;

    //Makes the board initially empty
    public Board(int boardsize){
        board = new ArrayList<ArrayList<BoardValues>>();
        for(int i = 0; i < boardsize; i++){
            ArrayList<BoardValues> row = new ArrayList<BoardValues>();
            for(int j = 0; j < boardsize; j++){
                row.set(j, BoardValues.EMPTY);
            }
            board.set(i, row);
        }
    }

    //Getter
    public ArrayList<ArrayList<BoardValues>> getBoard(){
        return board;
    }

    //Change value in the board
    public void changeBoardValue(int y, int x, BoardValues value){ // remember range for y and x is 0 to n-1 not 1 to n
        ArrayList<BoardValues> tempRow = board.get(y);
        tempRow.set(x, value);
        board.set(y, tempRow);
    }
}
