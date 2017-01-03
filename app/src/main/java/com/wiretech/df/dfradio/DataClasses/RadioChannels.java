package com.wiretech.df.dfradio.DataClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.wiretech.df.dfradio.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class RadioChannels {

    private static RadioChannels mInstance = new RadioChannels();

    public static RadioChannels getInstance() {
        return mInstance;
    }

    private RadioChannels() {
    }

    public List<Integer> mIds = new ArrayList<Integer>() {{
        add(0);
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
        add(7);
        add(8);
        add(9);
    }};

    public String mRadioNames[] = {
            // 0
            "Black Star Radio",
            // 1
            "Most Wanted Radio",
            // 2
            "Gangsta & Hip-Hop",
            // 3
            "R'n'B",
            // 4
            "Русский Рэп",
            // 5
            "Европа Плюс: R&B",
            // 6
            "Зайцев FM: Хип-Хоп",
            // 7
            "HipHop BARADA",
            // 8
            "HIPHOPBY",
            // 9
            "Хит FM: Urban",

    };

    public String mLocations[] = {
            // 0
            "Moscow, Russia",
            // 1
            "Moscow, Russia",
            // 2
            "Moscow, Russia",
            // 3
            "Moscow, Russia",
            // 4
            "Moscow, Russia",
            // 5
            "Moscow, Russia",
            // 6
            "Moscow, Russia",
            // 7
            "Moscow, Russia",
            // 8
            "Gomel, Belarus",
            // 9
            "Moscow, Russia",

    };

    public String mLinks[] = {
            // 0
            "http://blackstarradio.hostingradio.ru:8024/blackstarradio128.mp3",
            // 1
            "http://proxima.shoutca.st:9681/strea",
            // 2
            "http://ic3.101.ru:8000/c14_11?userid=0&setst=0ishp55pb4ok38gtbq4pl0ua20&tok=28512703qrfrVY2A%2Br240XuE%2FKiw%2FA%3D%3D1",
            // 3
            "http://ic7.101.ru:8000/c4_3?userid=0&setst=0ishp55pb4ok38gtbq4pl0ua20&tok=28512703qrfrVY2A%2Br240XuE%2FKiw%2FA%3D%3D2",
            // 4
            "http://ic8.101.ru:8000/c1_3?userid=0&setst=0ishp55pb4ok38gtbq4pl0ua20&tok=28512703qrfrVY2A%2Br240XuE%2FKiw%2FA%3D%3D5",
            // 5
            "http://eprnb128server.streamr.ru:8061/eprnb128",
            // 6
            "http://zaycevfm.cdnvideo.ru/ZaycevFM_rnb_128.mp3",
            // 7
            "http://listen1.myradio24.com:9000/4455",
            // 8
            "http://online.hiphop.by:9000/8000",
            // 9
            "http://icecast.radiohitfm.cdnvideo.ru/st04.mp3",

    };

    // Указать индекс (i - 1)
    public List<Integer> mLikes = new ArrayList<>();

    public static int mPlayRadioWithId = -1;
    public String mMetaDataNameSongPlayingRadio = null;

    public void saveLike(Context context, int n) {
        if(!mLikes.contains(n)) {
            mLikes.add(n);
            Collections.sort(mLikes);
        }
        SharedPreferences sPref = context.getSharedPreferences(context.getString(R.string.file_name), MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(String.valueOf(n), "like");
        ed.apply();
    }

    public void saveDislike(Context context, int n) {
        if(mLikes.contains(n)) {
            mLikes.remove(Integer.valueOf(n));
        }
        SharedPreferences sPref = context.getSharedPreferences(context.getString(R.string.file_name), MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString(String.valueOf(n), "dislike");
        ed.apply();
    }

    public void loadLikes(Context context) {
        SharedPreferences sPref = context.getSharedPreferences(context.getString(R.string.file_name), MODE_PRIVATE);
        mLikes.clear();
        for(int i = 0; i < mIds.size(); i++) {
            if(sPref.getString(String.valueOf(mIds.get(i)), "").equals("like")) {
                mLikes.add(mIds.get(i));
            }
        }
    }

}

/*
    public int mIds[] = {
            0,
            1,
            2,
            3,
            1,
            2,
            3,
            1,
            2,
            3,
            1,
            2,
            3
    };

    public String mRadioNames[] = {
            "CRAZY DREAM1",
            "CRAZY DREAM2",
            "CRAZY DREAM3",
            "CRAZY DREAM4",
            "CRAZY DREAM2",
            "CRAZY DREAM3",
            "CRAZY DREAM4",
            "CRAZY DREAM2",
            "CRAZY DREAM3",
            "CRAZY DREAM4",
            "CRAZY DREAM2",
            "CRAZY DREAM3",
            "CRAZY DREAM4"
    };

    public String mLocations[] = {
            "Moscow, Russia",
            "Piter, Russia",
            "Samara, Russia",
            "Rostov, Russia",
            "Piter, Russia",
            "Samara, Russia",
            "Rostov, Russia",
            "Piter, Russia",
            "Samara, Russia",
            "Rostov, Russia",
            "Piter, Russia",
            "Samara, Russia",
            "Rostov, Russia"
    };

    public String mLinks[] = {
            "http://proxy.sidedark-warez.pl/index.php?q=aHR0cDovL3N0cmVhbWluZy5yYWRpb25vbXkuY29tL1JhZGlvVGVzdC12MTA%3D",
            "http://leather-bg.com/index.php?q=aHR0cDovL3N0cmVhbWluZy5yYWRpb25vbXkuY29tL1JhZGlvVGVzdC12MTA%3D",
            "http://prx.afkcz.eu/prx/index.php?q=aHR0cDovL3N0cmVhbWluZy5yYWRpb25vbXkuY29tL1JhZGlvVGVzdC12MTA%3D",
            "http://efintrust.com/muro/index.php?q=aHR0cDovL3N0cmVhbWluZy5yYWRpb25vbXkuY29tL1JhZGlvVGVzdC12MTA%3D"
    };

    // Указать индекс (i - 1)
    public List<Integer> mLikes = new ArrayList<>();

    public int mPlayRadioWithId = -1;
}
*/