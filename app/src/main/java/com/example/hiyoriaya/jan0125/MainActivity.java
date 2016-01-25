package com.example.hiyoriaya.jan0125;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private GameView mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new GameView(this);
        setContentView(mView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mView.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mView.stop();
    }
}
