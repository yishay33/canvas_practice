package com.example.canvasandgraphics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    MyView myView;
    Thread thread;
    boolean userAskBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new MyView(this);
        thread = new Thread(myView);
        thread.start();

        setContentView(myView);

    }

    @Override
    public void finish() {
        super.finish();
        userAskBack = true;
        myView.threadRunning = false;
        while(true){
            try {
                thread.join();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            break;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(myView !=null){
            myView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("data", "pause");
        if(userAskBack){
            Log.d("data", "user ask back");
        }
        else if(myView != null){
            myView.pause();
        }
    }

}