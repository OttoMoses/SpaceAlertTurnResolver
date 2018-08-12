package com.example.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.otto.spacealertresolver.R;
import com.example.otto.spacealertresolver.Stations.ActionStation;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Objects;

import javax.xml.parsers.ParserConfigurationException;

public class SettingsScreen extends AppCompatActivity
{

    private int numPlayers ;
    private String shipName;
    private EditText shipNameEntry;
    private EditText playerCount;
    private Button setupGame;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings_screen);

        //Get Controls
        shipNameEntry = findViewById(R.id.ShipNameEntry);
        playerCount = findViewById(R.id.PlayerCountEntry);
        setupGame = findViewById(R.id.SetupGameButton);
        context = this;

        //setup button
        setupGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = "";

                if(shipNameEntry.getText().toString().equals(""))
                {
                    messageText += "Ship name can not be empty.";
                }
                if(playerCount.getText().toString().equals(""))
                {
                    if(!messageText.equals(""))
                    {
                        messageText += "\n";
                    }
                    messageText += "Player number can not be empty.";
                }
                else if(Integer.parseInt(playerCount.getText().toString()) == 0)
                {
                    if (!messageText.equals(""))
                    {
                        messageText += "\n";
                    }
                    messageText += "Player number can not be zero.";
                }
                else if(Integer.parseInt(playerCount.getText().toString()) > 5)
                {
                    if (!messageText.equals(""))
                    {
                        messageText += "\n";
                    }
                    messageText += "Max number of players is 5.";
                }
                if (messageText.equals(""))
                {
                    // start a new game
                    numPlayers = Integer.valueOf(playerCount.getText().toString());
                    shipName = shipNameEntry.getText().toString();
                    MainActivity.game.SetupGame(numPlayers, shipName);
                    Intent intent = new Intent(context,GameSetup.class);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Problem with entered data");
                    alert.setMessage(messageText);
                    alert.setCancelable(true);
                    AlertDialog dialog =  alert.create();
                    dialog.show();
                }

            }
        });

    }
}
