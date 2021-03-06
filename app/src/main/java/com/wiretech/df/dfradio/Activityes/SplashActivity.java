package com.wiretech.df.dfradio.Activityes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.wiretech.df.dfradio.Classes.AdControl;
import com.wiretech.df.dfradio.Classes.Player;
import com.wiretech.df.dfradio.Classes.RadioState;
import com.wiretech.df.dfradio.DataClasses.RadioChannels;
import com.wiretech.df.dfradio.R;

public class SplashActivity extends AppCompatActivity {

    private int mSecForSplashActivity = 1;
    private int mIterationTime = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Player.newPlayerInstatnce();


        AdControl.newAdControlInstance();
        AdControl.getInstance().setContext(this);
        AdControl.getInstance().enableAds();

        RadioState.context = this;

        RadioChannels.getInstance().loadLikes(this);

        Thread splashTimer = new Thread() {
            public void run() {
                try {
                    int splashTimer = 0;
                    while(splashTimer < (mSecForSplashActivity * 1000)) {
                        sleep(mIterationTime);
                        splashTimer += mIterationTime;
                    }
                    startActivity(new Intent(SplashActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        splashTimer.start();
    }
}
