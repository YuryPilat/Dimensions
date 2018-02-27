package com.example.managernew;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    MediaPlayer mp;
    ImageButton start;
    Button button_Continue;
    Button button_start_over;
    private final String NEW_GAME = "NewGame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);

        final Animation a = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final Animation a2 = AnimationUtils.loadAnimation(this, R.anim.alphabutton);
        final Animation a4 = AnimationUtils.loadAnimation(this, R.anim.alpha4);
        final Animation a5 = AnimationUtils.loadAnimation(this, R.anim.alpha5);
        final Animation a6 = AnimationUtils.loadAnimation(this, R.anim.alpha6);

        imageView3.startAnimation(a);
        imageView2.startAnimation(a);
        imageView4.startAnimation(a4);
        imageView5.startAnimation(a5);
        imageView6.startAnimation(a6);

        if (Root.getBoolean(NEW_GAME, true)) {
            button_Continue = (Button) findViewById(R.id.button_continue);
            button_start_over = (Button) findViewById(R.id.button_start_over);
            button_Continue.setVisibility(View.INVISIBLE);
            button_start_over.setVisibility(View.INVISIBLE);
            start = (ImageButton) findViewById(R.id.start);
            start.startAnimation(a2);
        } else {
            start = (ImageButton) findViewById(R.id.start);
            start.setVisibility(View.INVISIBLE);
            button_Continue = (Button) findViewById(R.id.button_continue);
            button_start_over = (Button) findViewById(R.id.button_start_over);
            button_Continue.startAnimation(a);
            button_start_over.startAnimation(a);
        }

        mp = MediaPlayer.create(this, R.raw.intro);
        mp.setLooping(false);
        mp.start();
    }

    public void onClick(View v) {
        if (mp.isPlaying()) {
            mp.stop();
        }
        switch (v.getId()) {
            case R.id.start:
                Root.setBoolean(NEW_GAME, false);
                start.setVisibility(View.INVISIBLE);
                this.finish();
                Intent intent = new Intent(StartActivity.this, MenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.start_activity_on, R.anim.start_activity_off);
                break;
            case R.id.button_continue:
                intent = new Intent(this, MainActivity.class);
                this.finish();
                startActivity(intent);
                break;
            case R.id.button_start_over:
                Root.setBoolean(NEW_GAME, true);
                intent = new Intent(this, this.getClass());
                finish();
                this.startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mp.stop();
        mp.release();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}





