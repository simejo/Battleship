package a8.battleship.Models;import android.util.Log;import java.util.ArrayList;import java.util.Collections;import a8.battleship.Logic.BoardValues;import a8.battleship.Logic.Constants;import a8.battleship.Logic.Functions;/** * Created by siljechristensen on 09.04.15. */public class AiPlayer extends Player {    private ArrayList<Integer> rndPos;    private String classname = "AiPlayer";    private String level;    private ArrayList<Integer> prioritizedMoves; //Where the prioritized moves will be saved    private int score;    public AiPlayer(){        Log.i(classname, "Ai player created");        this.score = 0;        //Makes a list with all the positions        rndPos = new ArrayList<Integer>();        for (int i = 0; i < 100; i++) {            rndPos.add(i);        }        Collections.shuffle(rndPos);    // THE RANDOM LIST        prioritizedMoves = new ArrayList<Integer>();        level = Constants.level;    }    public String getLevel(){        return this.level;    }    public void incrementScore(){        this.score += 10;    }    public void decrementScore(){        this.score -= 1;    }    // LOW MEDIUM HARD    /*    The LOW level chooses randomly.    The aiNextMoveLow() returns the next move     */    public int aiNextMoveLow() {        return rndPos.remove(0);    }    /*    The MEDIUM level, will prioritize the positions next to a successful hit (the neighbours)    The  the aiNextMoveMedium() returns the next move     */    public int aiNextMoveMedium(){        ArrayList<Integer> prioritizedMoves = new ArrayList<Integer>();     //Where the prioritized moves will be saved        Board opponentBoard = Constants.playerOne.getBoard();        int nextPos;        if(prioritizedMoves.size() == 0){            nextPos = rndPos.remove(0);        }        else{            nextPos = prioritizedMoves.remove(0);        }        int x = nextPos % Constants.boardSize;        int y = Functions.findY(nextPos, Constants.boardSize);        if(hitBoat(opponentBoard, x, y)){            updatePrioritizedMovesMedium(x, y);        }        //test        Log.i("AiPlayer", "aiNextMoveMedium() - next position is: x = " + x + " y = " + y);        return nextPos;    }    /*    Help method for the aiNextMoveMedium()    Updates the prioritized list, with new positions - if they are valid - in which they are om the rndPos list     */    public void updatePrioritizedMovesMedium(int x, int y){        if (x != 0 && x != Constants.boardSize-1){            int pos1 = Functions.findPos(x+1,y);            int pos2 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                rndPos.remove(pos2);            }        }        else if (x==0){            int pos1 = Functions.findPos(x+1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        else if (x==Constants.boardSize - 1){            int pos1 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        if(y != 0 && y!=Constants.boardSize -1){            int pos1 = Functions.findPos(x,y+1);            int pos2 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                rndPos.remove(pos2);            }        }        else if (y==0){            int pos1 = Functions.findPos(x,y+1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        else if (y==Constants.boardSize - 1){            int pos1 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        Log.i("AiPlayer", "updatePrioritizedMovesMedium");    }    /*    Help method to MEDIUM.    Returns true if the AI player hit a boat. Needs this to update the prioritized list correctly     */    public boolean hitBoat(Board board, int x, int y){        BoardValues value = board.getContentInACell(x,y);        if(value == BoardValues.EAST || value == BoardValues.WEST || value == BoardValues.NORTH || value == BoardValues.SOUTH ||value == BoardValues.MIDDLE ){            return true;        }        else{ return false;}    }    /*    The HIGH level, will know which part of the boat it hits, and take the rest of the boat during the next moves    The  the aiNextMoveHigh() returns the next move     */    public int aiNextMoveHard(){        prioritizedMoves = new ArrayList<Integer>();     //Where the prioritized moves will be saved        Board opponentBoard = Constants.playerOne.getBoard();        int nextPos;        if(prioritizedMoves.size() == 0){            nextPos = rndPos.remove(0);        }        else{            nextPos = prioritizedMoves.remove(0);        }        int x = nextPos % Constants.boardSize;        int y = Functions.findY(nextPos, Constants.boardSize);        updatePrioritizedMovesHard(x, y);        //test        Log.i("AiPlayer", "aiNextMoveHard() - next position is: x = " + x + " y = " + y);        return nextPos;    }    public void updatePrioritizedMovesHard(int x, int y){        Log.i("AI", "Hard");        BoardValues value = Constants.playerOne.getBoard().getContentInACell(x,y);        if(value.equals(BoardValues.EAST)){            //Middle/West (add) - East            int pos1 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        else if(value.equals(BoardValues.WEST)){            //West - Middle/East (add)            int pos1 = Functions.findPos(x+1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        else if(value.equals(BoardValues.NORTH)){            //North - Middle/South (add)            int pos1 = Functions.findPos(x,y+1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        else if(value.equals(BoardValues.SOUTH)){            //North - Middle/South (add)            int pos1 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }        }        else if(value.equals(BoardValues.MIDDLE_VERTICAL)){            //Add north/south/more middle vertical            int pos1 = Functions.findPos(x,y+1);            int pos2 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                rndPos.remove(pos2);            }        }        else if(value.equals(BoardValues.MIDDLE_HORIZONTAL)){            //Add north/south/more middle vertical            int pos1 = Functions.findPos(x+1,y);            int pos2 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                rndPos.remove(pos1);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                rndPos.remove(pos2);            }        }        Log.i("AiPlayer", "updatePrioritizedMovesHard - prioritizedMoves is " + prioritizedMoves + " RandomList is " + rndPos);    }    public int getScore(){        return score;    }    //Help to check the AIs moves    public String toString(){        String result ="";        if (getLevel().equals("low")){            result += "\n Next moves for AI easy is: " + rndPos;        }        else if (getLevel().equals("medium")){            result += "\n Medium: Prioritized list is " + prioritizedMoves + "\n And the rndPos list is " + rndPos;        }        else if (getLevel().equals("hard")){            result += "\n Hard: Prioritized list is " + prioritizedMoves + "\n And the rndPos list is " + rndPos;        }        return result;    }}