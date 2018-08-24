package com.otto.spacealertresolver.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.otto.spacealertresolver.Actions.PlayerAction;
import com.otto.spacealertresolver.R;

/**
 * Created by Otto on 1/28/2018.
 */

public class ActionAdapter extends BaseAdapter
{
    private final PlayerAction[] actions;
    private final Activity context;
    @Override
    public int getCount() {
        return actions.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        PlayerAction item = actions[position];
        View view = context.getLayoutInflater().inflate(R.layout.action_item,viewGroup,false);
        TextView txtName = view.findViewById(R.id.ActionNameText);
        String nameText = "T + " + (position + 1) + ": " + item.name;
        txtName.setText(nameText);
        return view;
    }

    public ActionAdapter(Activity context, PlayerAction[] actions)
    {
        this.context = context;
        this.actions = actions;

    }
}
