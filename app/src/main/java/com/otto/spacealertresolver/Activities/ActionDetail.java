package com.otto.spacealertresolver.Activities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.otto.spacealertresolver.Actions.HeroicMoveAction;
import com.otto.spacealertresolver.Actions.PlayerAction;
import com.otto.spacealertresolver.Activities.MainActivity;
import com.otto.spacealertresolver.Adapters.ActionAdapter;
import com.otto.spacealertresolver.R;

public class ActionDetail extends AppCompatActivity
{
    private int playerId;
    private int actionId;
    private ActionAdapter actionList;
    private PlayerAction[] actionOptions;
    private ListView actionListView;
    private TextView actionLabel;
    private String[] heroicMoves;
    private Pair<Integer,Integer> heroicLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_action_detail);

        actionListView = findViewById(R.id.ActionOptionsView);
        actionLabel = findViewById(R.id.ActionDetailLabel);

        actionLabel.setText("Select an action for time T+" + actionId + 1);

        //get position of the action being edited
        playerId = this.getIntent().getIntExtra("PlayerID", 0);
        actionId = this.getIntent().getIntExtra("ActionID", 0);

        actionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                PlayerAction action = actionOptions[position];
                if(action.getClass() == HeroicMoveAction.class)
                {
                    final Dialog dialog = new Dialog(ActionDetail.this);
                    dialog.setContentView(R.layout.heroic_move_dialog);
                    dialog.setTitle("Select heroic move destination");
                    ListView locations = dialog.findViewById(R.id.HeroicListView);
                    Adapter adapter = new ArrayAdapter<String>(ActionDetail.this, android.R.layout.simple_list_item_1, heroicMoves);
                    locations.setAdapter((ListAdapter) adapter);
                    dialog.show();
                    locations.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String location = heroicMoves[position];
                            switch (location)
                            {
                                case "Upper Red" :
                                {
                                    heroicLocation = new Pair<>(0,1);
                                    break;
                                }
                                case "Lower Red" :
                                {
                                    heroicLocation = new Pair<>(0,0);
                                    break;
                                }
                                case "Upper White" :
                                {
                                    heroicLocation = new Pair<>(1,1);
                                    break;
                                }
                                case "Lower White" :
                                {
                                    heroicLocation = new Pair<>(1,0);
                                    break;
                                }
                                case "Upper Blue" :
                                {
                                    heroicLocation = new Pair<>(2,1);
                                    break;
                                }
                                case "Lower Blue" :
                                {
                                    heroicLocation = new Pair<>(2,0);
                                    break;
                                }
                            }
                            PlayerAction action = new HeroicMoveAction(heroicLocation);
                            MainActivity.game.players[playerId].actions[actionId] = action;
                            dialog.dismiss();
                            finish();
                        }
                    });
                }
                else
                {
                    MainActivity.game.players[playerId].actions[actionId] = actionOptions[position];
                    finish();
                }
            }
        });
    }

    @Override
    protected  void onResume()
{
    super.onResume();
    actionOptions = MainActivity.game.actions;
    actionList = new ActionAdapter(this, actionOptions);
    actionListView.setAdapter(actionList);
    heroicMoves = new String[]{"Upper Red","Lower Red","Upper White","Lower White","Upper Blue","Lower Blue"};
}
}
