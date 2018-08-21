package com.otto.spacealertresolver.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.R;
import com.otto.spacealertresolver.ThreatString;

import java.util.ArrayList;

import static com.otto.spacealertresolver.Activities.MainActivity.game;

/**
 * Created by Otto on 1/28/2018.
 */

public class ThreatAdapter extends BaseAdapter
{
    private ThreatString[] threats;
    private Activity context;

    @Override
    public int getCount() {
        return threats.length;
    }

    @Override
    public Object getItem(int i) {
        return threats[i];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        ThreatString item = threats[position];
        View view = context.getLayoutInflater().inflate(R.layout.threat_item,viewGroup,false);
        TextView txtTurn = view.findViewById(R.id.ThreatTurnText);
        TextView txtName = view.findViewById(R.id.ThreatNameText);
        TextView txtTrack = view.findViewById(R.id.ThreatTrackText);
        txtTurn.setText("T+" + (position + 1));
        if(item.threatID != game.threatNames.size() + 1)
        {
            txtName.setText("Threat: " + game.threatNames.get(item.threatID));
        }
        else
        {
            txtName.setText("Threat: Not selected");
        }
        if(item.trackNum != 4)
        {
            String[] colors = new String[]
                    {
                            "Red",
                            "white",
                            "Blue",
                            "Internal",
                    };
            txtTrack.setText("Track: " + colors[item.trackNum]);
        }
        else
        {
            txtTrack.setText("Track: Not selected");
        }

        return view;
    }

    public ThreatAdapter(Activity context, ThreatString[] threats)
    {
        this.threats = threats;
        this.context = context;
    }
}
