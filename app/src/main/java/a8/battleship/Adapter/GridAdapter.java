package a8.battleship.Adapter;

/**
 * Created by siljechristensen on 17.03.15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import a8.battleship.Logic.BoardValues;
import a8.battleship.Models.Board;
import a8.battleship.R;
import a8.battleship.Tokens.Ship;


//The adapter is  a description of how the cell will look like (in this case in the gridView)


public class GridAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<Ship> ships;
    private Board board;


    //TODO: Need to decide if we should have BoardValues[][] or ArrayrList<ArrayList<BoardValues>>
    //Pros/cons in the agenda document, we are using arrayList

    //The constructor
    public GridAdapter(Context context, ArrayList<Ship> ships, Board board) {
        this.context = context;
        this.ships = ships;
        this.board = board;
    }

    //What to show, with the parameters so we can change the layout based on the different parameters
    //Position is the number in the array based on what cell you clicked

    /*Can we give this method a parameter which is a board/player, and a coordinate?
    So we can get the value in that cell? And then just call the
    cell.setImageResource(R.drawable.XXXXXXX);
    where XXXXXXX depends on what value the board has at a given coordinate?
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.grid_adapter_layout, null); //A reference to the adapter xml file

            ImageView cell = (ImageView) gridView.findViewById(R.id.cell);

            cell.setAdjustViewBounds(true); //Added this to remove the extra space between the cells in the grid

            //TODO: implement the board so we can choose the correct pictures to the right enums
            /*  TODO: * Connect a cell to a coordinate in a specific board
                (need to use a reference to a board, because we might have two boards during a game)
            */
            cell.setImageResource(R.drawable.empty);

           // String mobile = ships[position];
/*
            if (mobile.equals("Greece")) {
                cell.setImageResource(R.drawable.greekflag);
            } else if (mobile.equals("Germany")) {
                cell.setImageResource(R.drawable.germanflag);
            } else if (mobile.equals("Italy")) {
                cell.setImageResource(R.drawable.italianflag);
            } else {
                cell.setImageResource(R.drawable.britishflag);
            }
*/
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }

    //Below, there are basic methods to make the adapter work

    @Override
    public int getCount() {
        return 0; //ships.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
