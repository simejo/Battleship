package a8.battleship.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by siljechristensen on 09.04.15.
 */
public class AiPlayer extends Player {
    private ArrayList<Integer> rndPos;
    private String classname = "AiPlayer";
    private String level;


    public AiPlayer(){
        Log.i(classname, "Ai player created");

        //Makes a list with all the positions ans shuffles it
        rndPos = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            rndPos.add(i);
        }
        Collections.shuffle(rndPos);
        //NEED TO FIX THIS!!!!!!!
        level = "low";
    }

    public String getLevel(){
        return this.level;
    }


    // LOW MEDIUM HARD
    //Finds the next move for low level
    public int aiNextMoveLow() {
        return rndPos.remove(0);
    }
}
