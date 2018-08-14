package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.otto.spacealertresolver.R;

public class GameSetup extends AppCompatActivity
{
    private Context context = this;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_setup);

        //get controls
        Button playersButton = findViewById(R.id.PlayersEditButton);
        Button threatTracksButton = findViewById(R.id.ThreatTracksButton);
        Button threatsButton = findViewById(R.id.ThreatsButton);
        Button resolveButton = findViewById(R.id.ResolveButton);
        TextView title = findViewById(R.id.SetupLabel);

        //setup clickers for buttons
        playersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, PlayersList.class);
                startActivity(intent);
            }
        });

        threatTracksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context,ThreatTrackEdit.class);
                startActivity(intent);
            }
        });

        threatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, ThreatsList.class);
                startActivity(intent);
            }
        });

        resolveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context, ResultsScreen.class);
                startActivity(intent);
            }
        });


        if(MainActivity.game.gameType < 2)
        {
            title.setText("Threats are predefined for tutorial scenarios");
            threatTracksButton.setEnabled(false);
            threatsButton.setEnabled(false);

        }
    }
}
