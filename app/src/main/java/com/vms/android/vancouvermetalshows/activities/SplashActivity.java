package com.vms.android.vancouvermetalshows.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.vms.android.vancouvermetalshows.R;
import com.vms.android.vancouvermetalshows.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private Button mCheckShowsButton;
    private ImageButton mVMSImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getSupportActionBar().hide();

        mVMSImageView = findViewById(R.id.vms_logo);
        mVMSImageView.animate().alpha(1f).setDuration(9000);

        final Intent intent = new Intent(this, MainActivity.class);


        mVMSImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });



    }

}
