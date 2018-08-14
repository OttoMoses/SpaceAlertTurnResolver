package com.otto.spacealertresolver.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.otto.spacealertresolver.R;
import com.otto.spacealertresolver.ThreatTrack;

public class ThreatTrackEdit extends AppCompatActivity
{
    Spinner internalSpinner;
    private int redTrackID;
    private int whiteTrackID;
    private int blueTrackID;
    private int internalTrackID;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threat_track_edit);
        Button saveButton = findViewById(R.id.SaveTracks);
        Spinner redSpinner = findViewById(R.id.RedTrackSpinner);
        Spinner whiteSpinner = findViewById(R.id.WhiteTrackSpinner);
        Spinner blueSpinner = findViewById(R.id.BlueTrackSpinner);
        internalSpinner = findViewById(R.id.InternalTrackSpinner);


        //setup spinners
        ArrayAdapter tracksAdapter = ArrayAdapter.createFromResource(this, R.array.tracks_array, android.R.layout.simple_spinner_item);
        tracksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        redSpinner.setAdapter(tracksAdapter);
        redSpinner.setSelection(MainActivity.game.threatTracks[0].trackID);
        whiteSpinner.setAdapter(tracksAdapter);
        whiteSpinner.setSelection(MainActivity.game.threatTracks[1].trackID);
        blueSpinner.setAdapter(tracksAdapter);
        blueSpinner.setSelection(MainActivity.game.threatTracks[2].trackID);
        internalSpinner.setAdapter(tracksAdapter);
        internalSpinner.setSelection(MainActivity.game.threatTracks[3].trackID);

        //setup spinner selection listeners
        redSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                redTrackID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        whiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                whiteTrackID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        blueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                blueTrackID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        internalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                internalTrackID = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveTrack(0, redTrackID);
                SaveTrack(1, whiteTrackID);
                SaveTrack(2, blueTrackID);
                SaveTrack(3,internalTrackID);
                finish();
            }
        });
    }

    private void SaveTrack(int trackPosition, int ID)
    {
        ThreatTrack track = MainActivity.game.threatTracks[trackPosition];
        track.trackID = ID;
        switch(ID)
        {
            case 0:
                track.XSpace = new int[]{6};
                track.YSpaces = new int[]{};
                track.EndSpace = new int[]{10};
                track.rangeTwo = 0;
                track.rangeOne = 5;
                break;
            case 1:
                track.XSpace = new int[]{4};
                track.YSpaces = new int[]{};
                track.EndSpace = new int[]{11};
                track.rangeTwo = 1;
                track.rangeOne = 6;
                break;
            case 2:
                track.XSpace = new int[]{5};
                track.YSpaces = new int[]{10};
                track.EndSpace = new int[]{12};
                track.rangeTwo = 2;
                track.rangeOne = 7;
                break;
            case 3:
                track.XSpace = new int[]{5};
                track.YSpaces = new int[]{9};
                track.EndSpace = new int[]{13};
                track.rangeTwo = 3;
                track.rangeOne = 8;
                break;
            case 4:
                track.XSpace = new int[]{4};
                track.YSpaces = new int[]{8};
                track.EndSpace = new int[]{14};
                track.rangeTwo = 4;
                track.rangeOne = 9;
                break;
            case 5:
                track.XSpace = new int[]{6};
                track.YSpaces = new int[]{9,13};
                track.EndSpace = new int[]{15};
                track.rangeTwo = 5;
                track.rangeOne = 10;
                break;
        }
    }

}
