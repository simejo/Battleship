package a8.battleship.Logic;

import java.util.ArrayList;

import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.Tokens.Ship;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Functions {

    //A method to find the ship that is hit
    //TODO: FINISH THIS, OR REMOVE?????
    public static void findAndUpdateShip(int x, int y, Player opponent){
        BoardValues value = opponent.getBoard().getContentInACell(x,y);
        if (value == BoardValues.EAST){
            findAndUpdateShip(x - 1, y,  opponent);
        } else if (value == BoardValues.MIDDLE_HORIZONTAL | value == BoardValues.MIDDLE_HORIZONTAL_DESTROYED){
            findAndUpdateShip(x - 1, y, opponent);
        }
        else if (value == BoardValues.MIDDLE_VERTICAL | value == BoardValues.MIDDLE_VERTICAL_DESTROYED){
            findAndUpdateShip(x, y - 1, opponent);
        }
        else if (value == BoardValues.SOUTH | value == BoardValues.SOUTH_DESTROYED){
            findAndUpdateShip(x, y - 1, opponent);
        }
        else if (value == BoardValues.NORTH | value == BoardValues.NORTH_DESTROYED){
            //Gets the shipList to the opponent
            ArrayList<Ship> shipsList = opponent.getBoard().getShipArray();
            for (int i = 0; i < shipsList.size(); i++){
                //Checking the ship
                Ship ship = shipsList.get(i);
                if (x == ship.getX() && y == ship.getY()){
                    //ship.decreasePartsLeft();
                }
            }
        }
        else if (value == BoardValues.WEST | value == BoardValues.WEST_DESTROYED ){
            //Gets the shipList to the opponent
            ArrayList<Ship> shipsList = opponent.getBoard().getShipArray();
            for (int i = 0; i < shipsList.size(); i++){
                //Checking the ship
                Ship ship = shipsList.get(i);
                if (x == ship.getX() && y == ship.getY()){
                    //DECREASING PARTS LEFT
                    ship.decreasePartsLeft();
                }
            }
        }
    }

    //A method that checks if a player has won
    public static boolean endGame(Board board){
        for (ArrayList<BoardValues> rows : board.getBoard()){
            for(BoardValues cell : rows){
                if(cell == BoardValues.WEST || cell == BoardValues.EAST || cell == BoardValues.NORTH || cell == BoardValues.SOUTH
                        || cell == BoardValues.MIDDLE_HORIZONTAL || cell == BoardValues.MIDDLE_VERTICAL){
                    return false;

                }
            }
        }
        return true;
    }


    //Finding y position depending on the position given in the gridView
    public static int findY(int position){
        return position/ Variables.boardSize;
    }

    //Finding x position depending on the position given in the gridView
    public static int findX(int position){
        return position% Variables.boardSize;
    }


    //Finding position depending on x and y coordinates
    public static int findPos(int x, int y){
        return y* Variables.boardSize + x;
    }

}
