package com.example.managernew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class HeroFemaleActivity extends AppCompatActivity  {

    private boolean mShowingBack = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_female);
        Toast toast = Toast.makeText(getApplicationContext(), R.string.toast_text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        assert frameLayout != null;
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
    }

    private void flipCard() {
        if (mShowingBack) {
            mShowingBack = false;
            getFragmentManager().popBackStack();
        } else {
            mShowingBack = true;
            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.animator.card_flip_right_enter, R.animator.card_flip_right_exit,
                            R.animator.card_flip_left_enter, R.animator.card_flip_left_exit)
                    .replace(R.id.container, new CardBackFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.any_activity_on, R.anim.any_activity_off);
                break;
            case R.id.btnChooseFemale:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.any_activity_on, R.anim.any_activity_off);
                break;
        }
    }
}
