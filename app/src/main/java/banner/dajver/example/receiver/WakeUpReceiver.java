package banner.dajver.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import banner.dajver.example.service.CheckScreenStatusService;

/**
 * Created by Admin on 12.10.2015.
 */
public class WakeUpReceiver extends BroadcastReceiver {

    private boolean screenOff;
    public static final String SCREEN_STATE = "screen_state";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            screenOff = true;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            screenOff = false;
        }

        Intent i = new Intent(context, CheckScreenStatusService.class);
        i.putExtra(SCREEN_STATE, screenOff);
        context.startService(i);
    }
}