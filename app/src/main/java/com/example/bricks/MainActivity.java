package com.example.bricks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar mStartCountBricksSeekBar;
    private TextView mStartCountBricksTextView;
    private int mCountBricks;
    private final String COUNT_BRICKS = "count bricks";
    private final String DIFFICULTY = "difficulty";
    private int mDifficulty=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartCountBricksSeekBar = findViewById(R.id.startCountBricksSeekBar);
        mStartCountBricksSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        mStartCountBricksTextView = findViewById(R.id.startCoutnBricksTextView);

        mCountBricks = mStartCountBricksSeekBar.getProgress() + 15;
        mStartCountBricksTextView.append(" "+ mCountBricks);

        RadioGroup radioGroup = findViewById(R.id.difficultyRadioGroup);
        radioGroup.setOnCheckedChangeListener(listener);

    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            TextView textView = findViewById(R.id.textView);
            switch (checkedId){
                case R.id.radioButton:
                    textView.setText("Сложность: последние кирпичики заберет!");
                    mDifficulty=1;
                    break;
                case R.id.radioButton2:
                    textView.setText("Сложность: придется подумать!");
                    mDifficulty = 2;
                    break;
                case R.id.radioButton3:
                    textView.setText("Сложность: надежды на выигрыш нет!");
                    mDifficulty = 3;
                    break;
            }
        }
    };

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(COUNT_BRICKS, mCountBricks);
        intent.putExtra(DIFFICULTY, mDifficulty);
        startActivity(intent);
    }


}
