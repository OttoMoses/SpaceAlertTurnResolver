package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.otto.spacealertresolver.Game;
import com.otto.spacealertresolver.R;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    public static Game game;
    Button helpButton;
    Button contactButton;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set content view
        setContentView(R.layout.activity_main);

        //get controls
        Button newGameButton = findViewById(R.id.NewGameButton);

        //set up listeners
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    game = new Game(context);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, SettingsScreen.class);
                startActivity(intent);
            }
        });
    }
}