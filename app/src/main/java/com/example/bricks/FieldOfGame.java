package com.example.bricks;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

public class FieldOfGame extends AsyncTask<Void, Integer, Void> {

    private int mBeginSleep;
    private int mBeginCount;
    private int mFinalCount;
    private String mText;
    private TextView mCountView;
    private TextView mTextView;

    FieldOfGame(int beginSleep, int beginCount, int finalCount, TextView countView, String text, TextView textView){
        mBeginSleep = beginSleep;
        mBeginCount = beginCount;
        mFinalCount = finalCount;
        mCountView = countView;
        mText = text;
        mTextView = textView;
    }


    @Override
    protected void onPreExecute() {
        mTextView.setText(mText);
        SystemClock.sleep(mBeginSleep);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (mBeginCount < mFinalCount){
            for (int i = mBeginCount; i<=mFinalCount; i++){
                publishProgress(i);
                SystemClock.sleep(200);
            }
        }
        else {
            for (int i = mBeginCount; i >= mFinalCount; i--) {
                publishProgress(i);
                SystemClock.sleep(200);
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mCountView.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
