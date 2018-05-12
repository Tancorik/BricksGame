package com.example.bricks;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
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
    protected TextView mHistoryText;
    private boolean yourMove = true;
    Random random = new Random();
    private Button mRestartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        mCountBricks = intent.getIntExtra(COUNT_BRICKS, 15);

        mHelpTextView = findViewById(R.id.helpTextView);
        mCountBricksTextView = findViewById(R.id.countBricksTextView);
        mCountBricksTextView.setText(String.valueOf(mCountBricks));

        mHistoryText = findViewById(R.id.historyTextView);
        mHistoryText.setText(String.valueOf(mCountBricks));

        mRestartButton = findViewById(R.id.restartButton);

        gameManager();
    }

    public void onClickMoveButton(View view) {
        if (mCountBricks>0) {
            int move = 0;
            switch (view.getId()) {
                case R.id.oneButton:
                    move = 1;
                    break;
                case R.id.twoButton:
                    move = 2;
                    break;
                case R.id.threeButton:
                    move = 3;
                    break;
            }

            if (move > mCountBricks) move = mCountBricks;
            //Toast.makeText(getApplicationContext(), "Вы убрали " + move + " кирпичик(a)!", Toast.LENGTH_SHORT).show();
            mCountBricks -= move;
            mCountBricksTextView.setText(String.valueOf(mCountBricks));
            mHistoryText.append(" ->You("+move+")-> "+mCountBricks);
            checkVictory();
            yourMove = false;
            gameManager();
        }
    }

    private void gameManager(){
        int move = 0;
        if (!yourMove && mCountBricks>0) {
            move = getMove();
            mCountBricks -= move;
            mHelpTextView.setText("Андройд сделал ход в " + move + " кирпичика!\nВаш ход!");
            mCountBricksTextView.setText(String.valueOf(mCountBricks));
            mHistoryText.append(" ->Ard("+move+")-> "+mCountBricks);
            checkVictory();
            yourMove = true;
        } else {
            mHelpTextView.setText("Ваш ход!");
        }
    }

    // проверка завершения игры и кто выиграл!
    private void checkVictory(){
        Toast toast;
        if (mCountBricks == 0){
            if (yourMove){
               mCountBricksTextView.setText("Победа!");
               mHelpTextView.setText("Вы забрали последние кирпичики!");
            }
            else{
                mCountBricksTextView.setText("Продул!");
                mHelpTextView.setText("Андройд забрал последние кирпичики!");
            }
            mRestartButton.setVisibility(View.VISIBLE);
        }

    }

    // вычисляет ход андройда
    private int getMove(){
        int move=0;
        if (mCountBricks<=3){
            move = mCountBricks % 4;
        }
        else {
            move = random.nextInt(3) + 1;
        }
        return move;
    }

    public void onClickRestart(View view) {
        Intent intent = getIntent();
        mCountBricks = intent.getIntExtra(COUNT_BRICKS, 15);

        mCountBricksTextView.setText(String.valueOf(mCountBricks));
        mHistoryText.setText(String.valueOf(mCountBricks));

        mRestartButton.setVisibility(View.INVISIBLE);
        gameManager();
    }
}
