package banner.dajver.example.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;

import banner.dajver.example.receiver.WakeUpReceiver;

/**
 * Created by Admin on 12.10.2015.
 */
public class CheckScreenStatusService extends Service {

    public static final String FIRST_RUN = "firstrun";
    public static final int TIMER_DELAY = 60000;
    public static int launchCounter = 0;

    private BroadcastReceiver mReceiver = null;
    private SharedPreferences prefs = null;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mReceiver = new WakeUpReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = false;
        if (prefs.getBoolean(FIRST_RUN, true)) {
            prefs.edit().putBoolean(FIRST_RUN, false).commit();
        } else {
            try {
                screenOn = intent.getBooleanExtra(WakeUpReceiver.SCREEN_STATE, false);
                if (!screenOn) {
                    launchCounter++;
                    startBanner();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void startBanner() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(launchCounter <= 3)
                    startService(new Intent(CheckScreenStatusService.this, BannerService.class));
            }
        }, TIMER_DELAY);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if(mReceiver != null)
            unregisterReceiver(mReceiver);

    }
}