package banner.dajver.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import banner.dajver.example.service.CheckScreenStatusService;
import banner.dajver.example.service.DayStateService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, CheckScreenStatusService.class));
        startService(new Intent(this, DayStateService.class));
        finish();
    }
}
