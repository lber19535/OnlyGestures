package og.bill.com.onlygestures;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import og.bill.com.onlygestures.service.GesturesBackendService;

/**
 * Created by bill on 2017/11/1.
 */

public class App extends Application {

    private static final String TAG = "Gesture App";

    @Override
    public void onCreate() {
        super.onCreate();
        if (Settings.canDrawOverlays(this)){
            bindGestureService();
        }

    }

    public void bindGestureService() {
        Log.d(TAG, "bind gesture service");

        Intent intent = new Intent(this, GesturesBackendService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "gesture service connected");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "gesture service disconnected");
            }
        }, BIND_AUTO_CREATE);
    }
}
