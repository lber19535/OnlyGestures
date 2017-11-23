package og.bill.com.onlygestures;

import android.app.WallpaperManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;

import java.util.List;

import og.bill.com.onlygestures.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.gestureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkDrawOverlayPermission();
                }
            }
        });


    }

    /**
     * code to post/handler request for permission
     */
    public final static int REQUEST_CODE = 11;

    public void checkDrawOverlayPermission() {
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(this)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /** check if received result code
         is equal our requested code for draw permission  */
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                Log.d(TAG, "Overlays permission was granted");
                App app = (App) getApplication();
                app.bindGestureService();
            } else {
                binding.gestureSwitch.setChecked(false);
            }
        }
    }
}
