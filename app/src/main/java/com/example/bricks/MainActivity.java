package com.example.bricks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar mStartCountBricksSeekBar;
    private TextView mStartCountBricksTextView;
    private int mCountBricks;
    private final String COUNT_BRICKS = "count bricks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartCountBricksSeekBar = findViewById(R.id.startCountBricksSeekBar);
        mStartCountBricksSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        mStartCountBricksTextView = findViewById(R.id.startCoutnBricksTextView);

        mCountBricks = mStartCountBricksSeekBar.getProgress() + 15;
        mStartCountBricksTextView.append(" "+ mCountBricks);


    }

    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mCountBricks = mStartCountBricksSeekBar.getProgress() + 15;
            String string = getString(R.string.startCountBricksText);
            mStartCountBricksTextView.setText(string + " " + mCountBricks);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void onClickStart(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(COUNT_BRICKS, mCountBricks);
        startActivity(intent);
    }
}
