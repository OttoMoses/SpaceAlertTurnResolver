package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.otto.spacealertresolver.Player;
import com.otto.spacealertresolver.Adapters.PlayerAdapter;
import com.otto.spacealertresolver.R;

public class PlayersList extends AppCompatActivity
{
    private ListView playerListView;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        context = this;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_players_list);
        playerListView = findViewById(R.id.PlayerListView);
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent playerData = new Intent(context, PlayerDetail.class);
                playerData.putExtra("PlayerID", position);
                startActivity(playerData);
            }
        });
    }
    @Override
    protected  void onResume()
    {
        super.onResume();
        Player[] players = MainActivity.game.players;
        PlayerAdapter playerList = new PlayerAdapter(this, players);
        playerListView.setAdapter(playerList);
    }
}
