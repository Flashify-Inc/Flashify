package com.example.flashify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MagicView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_view);
    }

    public void launchMagicTextView(View V){
        Intent intentS = new Intent(this,MagicTextView.class);
        startActivity(intentS);
    }
}