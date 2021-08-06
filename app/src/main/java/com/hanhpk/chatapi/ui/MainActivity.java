package com.hanhpk.chatapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.CircleSprite;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.hanhpk.chatapi.R;
import com.hanhpk.chatapi.exception.NoInternetConnectionException;
import com.hanhpk.chatapi.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Sprite sprite = new FoldingCube();
        progressBar.setIndeterminateDrawable(sprite);

        if (!Utils.checkNetwork(this)){
            try {
                throw new NoInternetConnectionException();

            } catch (NoInternetConnectionException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return;
        }
        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this,ChatActivity.class));
            finish();
        },3000);

    }
}