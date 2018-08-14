package com.otto.spacealertresolver.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.R;

/**
 * Created by Otto on 1/26/2018.
 */

public class PlayerAdapter extends BaseAdapter {

    private Player[] players;
    private Activity context;

    @Override
    public int getCount() {
        return players.length;
    }

    @Override
    public Object getItem(int i) {
        return players[i];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        Player item = players[position];
        View view = context.getLayoutInflater().inflate(R.layout.player_item,viewGroup,false);
        TextView txtName = view.findViewById(R.id.PlayerNameText);
        txtName.setText(item.playerName);
        return view;
    }
    public PlayerAdapter(Activity context, Player[] players)
    {
        this.context = context;
        this.players = players;

    }
}
