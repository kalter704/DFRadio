package com.wiretech.df.dfradio.Activityes;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wiretech.df.dfradio.Classes.AdControl;
import com.wiretech.df.dfradio.Classes.Player;
import com.wiretech.df.dfradio.Classes.RadioState;
import com.wiretech.df.dfradio.DataClasses.Const;
import com.wiretech.df.dfradio.DataClasses.RadioChannels;
import com.wiretech.df.dfradio.Fragments.AllRadioFragment;
import com.wiretech.df.dfradio.Adapters.ViewPagerAdapter;
import com.wiretech.df.dfradio.Fragments.FavoriteRadioFragment;
import com.wiretech.df.dfradio.R;
import com.wiretech.df.dfradio.Services.NotificationService;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        initializeUI();

    }

    private void initializeUI() {
        (findViewById(R.id.rlMenu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllRadioFragment(), getString(R.string.tab_all));
        adapter.addFragment(new FavoriteRadioFragment(), getString(R.string.tab_favorite));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (RadioState.state != RadioState.State.STOP) {
            Intent closeIntent = new Intent(this, NotificationService.class);
            closeIntent.setAction(Const.ACTION.STOPFOREGROUND_ACTION);
            closeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pcloseIntent = PendingIntent.getService(this, 0, closeIntent, 0);
            try {
                pcloseIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
        Player.offRadio();
        AdControl.getInstance().disableAds();
    }


}
