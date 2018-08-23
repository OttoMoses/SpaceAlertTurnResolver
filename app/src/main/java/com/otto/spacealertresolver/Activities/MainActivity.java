package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.crashlytics.android.Crashlytics;
import com.otto.spacealertresolver.Game;
import com.otto.spacealertresolver.R;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    public static Game game;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        //set content view
        setContentView(R.layout.activity_main);

        //get controls
        Button newGameButton = findViewById(R.id.NewGameButton);
        Button creditsButtom = findViewById(R.id.CreditsButton);

        //set up listeners
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    game = new Game();
                Intent intent = new Intent(context, SettingsScreen.class);
                startActivity(intent);
            }
        });
        creditsButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreditsScreen.class);
                startActivity(intent);
            }
        });
    }
}