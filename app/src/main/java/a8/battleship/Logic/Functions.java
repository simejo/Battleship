package a8.battleship.Logic;

import java.util.ArrayList;

import a8.battleship.Models.Board;
import a8.battleship.Models.Player;
import a8.battleship.Tokens.Ship;

/**
 * Created by majakirkerod on 11.03.15.
 */
public class Functions {


    /* USED IN: BattleView
     * This method is used when we need to find the position to a ship
     * If we hit a south-part/middle_vertical-part, we need to get to the north-part
     * If we hit a east-part/middle_horizontal-part, we need to get to the west-part
     * -- and then find the ship based on the x and y coordinate, to update the partsLeft variable to the ship
     * TODO: Test if this works
     */
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

    public static boolean endGame(Board board){
        for (ArrayList<BoardValues> rows : board.getBoard()){
            for(BoardValues cell : rows){
                if(cell == BoardValues.WEST || cell == BoardValues.EAST || cell == BoardValues.NORTH || cell == BoardValues.SOUTH
                        || cell == BoardValues.MIDDLE || cell == BoardValues.MIDDLE_HORIZONTAL || cell == BoardValues.MIDDLE_VERTICAL){
                    return false;

                }
            }
        }
        return true;
    }


    //FINDING Y POSITION WHEN YOU ONLY HAVE A INT FOR THE POS
    public static int findY(int position){
        return position/Constants.boardSize;
    }

    //FINDING X POSITION WHEN YOU ONLY HAVE A INT FOR THE POS
    public static int findX(int position){
        return position%Constants.boardSize;
    }


    //FINDING TOTAL POS BASED ON X AND Y
    public static int findPos(int x, int y){
        return y*Constants.boardSize + x;
    }

}
