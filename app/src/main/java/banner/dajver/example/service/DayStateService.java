package banner.dajver.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 12.10.2015.
 */
public class DayStateService extends Service {

    private Timer timer = new Timer();
    private  long UPDATE_INTERVAL = TimeUnit.HOURS.toMillis(24); ;
    private static long DELAY_INTERVAL = TimeUnit.HOURS.toMillis(24);


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                CheckScreenStatusService.launchCounter = 0;
            }
        }, DELAY_INTERVAL, UPDATE_INTERVAL);
    }
}