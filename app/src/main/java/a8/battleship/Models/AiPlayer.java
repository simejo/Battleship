package a8.battleship.Models;import java.util.ArrayList;import java.util.Collections;import a8.battleship.Logic.BoardValues;import a8.battleship.Logic.Variables;import a8.battleship.Logic.Functions;//This class creates and holds the attributes to the AI player.public class AiPlayer extends Player {    //Array with the the random position. Used to choose the next moves for AI.    private ArrayList<Integer> rndPos;    //Array Where the prioritized moves will be saved. Used to choose the next moves for AI.    private ArrayList<Integer> prioritizedMoves;    //Level value will be easy, medium or hard.    private String level;    /**     * The constructor.     */    public AiPlayer(){        //Creates a list with all the positions in the board.        rndPos = new ArrayList<>();        for (int i = 0; i < Variables.boardSize*Variables.boardSize; i++) {            rndPos.add(i);        }        //Shuffles the list to get random positions from the board.        Collections.shuffle(rndPos);        this.prioritizedMoves = new ArrayList<>();        level = Variables.level;    }    /**     * Getter to the AI level.     * @return Returns the difficulty of the AI.     */    public String getLevel(){        return this.level;    }    /**     * The Easy level chooses randomly from the list.     * @return The next move for the AI.     */    public int aiNextMoveEasy() {        return rndPos.remove(0);    }    /**     * The Medium level will prioritize the positions next to a successful hit (the neighbours).     * @return The next move for the AI.     */    public int aiNextMoveMedium(){        Board opponentBoard = Variables.playerOne.getBoard();        int nextPos;        if(prioritizedMoves.size() == 0){            nextPos = rndPos.remove(0);        }        else{            nextPos = prioritizedMoves.remove(0);        }        int x = Functions.findX(nextPos);        int y = Functions.findY(nextPos);        if(hitBoat(opponentBoard, x, y)){            updatePrioritizedMovesMedium(x, y);        }        return nextPos;    }    /**     * Updates the prioritized list, with new positions - if they are valid - in which they are om the rndPos list     * @param x The x-coordinate     * @param y The y-coordinate     */    public void updatePrioritizedMovesMedium(int x, int y){        if (x != 0 && x != Variables.boardSize-1){            int pos1 = Functions.findPos(x+1,y);            int pos2 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                removeFromList(pos2, rndPos);            }        }        else if (x==0){            int pos1 = Functions.findPos(x+1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }        else if (x== Variables.boardSize - 1){            int pos1 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }        if(y != 0 && y!= Variables.boardSize -1){            int pos1 = Functions.findPos(x,y+1);            int pos2 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                removeFromList(pos2, rndPos);            }        }        else if (y==0){            int pos1 = Functions.findPos(x,y+1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }        else if (y== Variables.boardSize - 1){            int pos1 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }    }    /**     * Returns true if the AI player hit a boat. Needs this to update the prioritized list correctly     * @param board The boat you are shooting at     * @param x The x-coordinate     * @param y The y-coordinate     * @return True or false depending on if you hit or not     */    public boolean hitBoat(Board board, int x, int y){        BoardValues value = board.getContentInACell(x,y);        return (value == BoardValues.EAST || value == BoardValues.WEST || value == BoardValues.NORTH || value == BoardValues.SOUTH ||value == BoardValues.MIDDLE_HORIZONTAL|| value == BoardValues.MIDDLE_VERTICAL );    }    /**     * The hard level will know which part of the boat it hits, and take the rest of the boat during the next moves     * @return The next move for the AI.     */    public int aiNextMoveHard(){        int nextPos;        if(prioritizedMoves.size() == 0){            nextPos = rndPos.remove(0);        }        else{            nextPos = prioritizedMoves.remove(0);        }        int x = nextPos % Variables.boardSize;        int y = Functions.findY(nextPos);        updatePrioritizedMovesHard(x, y);        return nextPos;    }    /**     * Updates the prioritized moves for the hard AI player.     * @param x The x-coordinate     * @param y The y-coordinate     */    public void updatePrioritizedMovesHard(int x, int y){        BoardValues value = Variables.playerOne.getBoard().getContentInACell(x,y);        if(value.equals(BoardValues.EAST)){            //Add middle_horizontal/West            int pos1 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }        else if(value.equals(BoardValues.WEST)){            //Add middle_horizontal/East            int pos1 = Functions.findPos(x+1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }        else if(value.equals(BoardValues.NORTH)){            //Add middle_vertical/South            int pos1 = Functions.findPos(x,y+1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }        else if(value.equals(BoardValues.SOUTH)){            //Add north/middle_vertical            int pos1 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }        }        else if(value.equals(BoardValues.MIDDLE_VERTICAL)){            //Add north/south/more middle vertical            int pos1 = Functions.findPos(x,y+1);            int pos2 = Functions.findPos(x,y-1);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                removeFromList(pos2, rndPos);            }        }        else if(value.equals(BoardValues.MIDDLE_HORIZONTAL)){            //Add north/south/more middle vertical            int pos1 = Functions.findPos(x+1,y);            int pos2 = Functions.findPos(x-1,y);            if (rndPos.contains(pos1)){                prioritizedMoves.add(pos1);                removeFromList(pos1, rndPos);            }            if (rndPos.contains(pos2)){                prioritizedMoves.add(pos2);                removeFromList(pos2, rndPos);            }        }    }    /**     * Help method to remove an object in a specified list. Need this to remove the actual object and     * not the position in the list when we work with Integers     * @param removeObject Object to be removed     * @param list The list where the object should be removed     */    public void removeFromList(Object removeObject, ArrayList<Integer> list){        list.remove(removeObject);    }}