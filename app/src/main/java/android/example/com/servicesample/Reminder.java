package android.example.com.servicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class Reminder extends Service {
    Handler mHandler;
    Runnable r;

    int push_time = 5;
    private TimerTask NoticeTimerTask;
    private Handler handler;
    Timer timer;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        handler = new Handler();
        timer = new Timer();
        NoticeTimerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Reminder", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        if (timer != null) {
            timer.schedule(NoticeTimerTask, 0, push_time * 1000);
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        		stopSelf();

    }
}