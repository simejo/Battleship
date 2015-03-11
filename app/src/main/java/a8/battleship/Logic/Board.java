package a8.battleship.Logic;

import java.util.ArrayList;

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

    public ArrayList<ArrayList<BoardValues>> getBoard(){
        return board;
    }
}
