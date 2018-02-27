package com.example.managernew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton2:
            Intent intent = new Intent(this, HeroFemaleActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.any_activity_on, R.anim.any_activity_off);
            break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
