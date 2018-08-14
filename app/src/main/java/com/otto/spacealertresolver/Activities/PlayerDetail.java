package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.otto.spacealertresolver.Adapters.ActionAdapter;
import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Actions.PlayerAction;
import com.otto.spacealertresolver.R;

public class PlayerDetail extends AppCompatActivity
{
    private int playerId;
    private Player player;
    private PlayerAction[] actions;
    private EditText nameEntry;
    private ListView actionListView;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_player_detail);

        nameEntry = findViewById(R.id.PlayerNameEntry);
        actionListView = findViewById(R.id.ActionListView);
        Button saveButton = findViewById(R.id.SavePlayer);

        //get the player object to edit
        playerId = this.getIntent().getIntExtra("PlayerID", 0);
        player = MainActivity.game.players[playerId];
        nameEntry.setText(player.playerName);

        //button listeners
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(nameEntry.getText().toString().equals(""))
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Problem with entered data");
                    alert.setMessage("Player name can not be blank");
                    alert.setCancelable(true);
                    AlertDialog dialog =  alert.create();
                    dialog.show();
                }
                else
                {
                    player.playerName = nameEntry.getText().toString();
                    player.actions = actions;
                    MainActivity.game.players[playerId] = player;
                    finish();
                }

            }
        });
        actionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent actionData = new Intent(context,ActionDetail.class);
                actionData.putExtra("PlayerID", playerId);
                actionData.putExtra("ActionID", position);
                startActivity(actionData);
            }
        });
    }

    @Override
    protected  void onResume()
{
    super.onResume();

    actions = MainActivity.game.players[playerId].actions;
    ActionAdapter actionList = new ActionAdapter(this, actions);
    actionListView.setAdapter(actionList);
}
}
