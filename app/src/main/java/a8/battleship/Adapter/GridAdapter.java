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

import a8.battleship.R;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private final String[] ships;

    public GridAdapter(Context context, String[] ships) {
        this.context = context;
        this.ships = ships;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(R.layout.grid_adapter_layout, null);

            ImageView cell = (ImageView) gridView.findViewById(R.id.cell);
            cell.setImageResource(R.drawable.south);

            String mobile = ships[position];
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

    @Override
    public int getCount() {
        return ships.length;
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
