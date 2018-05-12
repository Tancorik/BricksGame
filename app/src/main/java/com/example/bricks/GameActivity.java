package com.example.bricks;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    final String COUNT_BRICKS = "count bricks";
    private int mCountBricks;
    protected TextView mCountBricksTextView;
    protected TextView mHelpTextView;
    final String mFlag = "Ваш ход!";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        mCountBricks = intent.getIntExtra(COUNT_BRICKS, 17);
        mHelpTextView = findViewById(R.id.helpTextView);
        mCountBricksTextView = findViewById(R.id.countBricksTextView);
        mCountBricksTextView.setText(String.valueOf(mCountBricks));

    }

    public void onClickDiceButton(View view) {
    }

    public void onClickMoveButton(View view) {
        switch (view.getId()){

        }
    }
}
