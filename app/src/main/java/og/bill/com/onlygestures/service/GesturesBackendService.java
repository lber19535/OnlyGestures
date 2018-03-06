package og.bill.com.onlygestures.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

import og.bill.com.onlygestures.R;
import og.bill.com.onlygestures.utils.Commands;
import og.bill.com.onlygestures.utils.ExecCommandCallback;
import og.bill.com.onlygestures.utils.RootUtils;

/**
 * Created by bill on 2017/11/1.
 */

public class GesturesBackendService extends Service {
    private static final String TAG = "GesturesBackendService";

    private static final String TAKE_SCREEN_SHOT_TAG = "take screen shot";
    private static final String HOME_TAG = "home";
    private static final String BACK_TAG = "back";

    private WindowManager mWindowManager;

    private boolean mEventDown = false;
    private boolean mEventMove = false;
    private boolean mEventUp = true;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        createGesturePanel();
    }

    private void createGesturePanel() {
        createRightCenterPanel();

        createCenterBottomPanel();

        createRightBottomPanel();
    }

    private void createCenterBottomPanel() {
        createPanel(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, () -> {
            System.out.println("1");
        });
    }

    private void createRightCenterPanel() {
        createPanel(Gravity.RIGHT | Gravity.CENTER_VERTICAL, () -> {
            System.out.println("2");
        });
    }

    private void createRightBottomPanel() {
        createPanel(Gravity.RIGHT | Gravity.BOTTOM, () -> {
            System.out.println("3");
        });
    }

    private void createPanel(int gravity, OnPanelSlideListener slideListener) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.gesture_area, null);

        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        view.getChildAt(0).setOnTouchListener((v, event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            if (mEventUp) {
                                mEventDown = true;
                                mEventMove = false;
                                mEventUp = false;
                            }
                            break;
                        }
                        case MotionEvent.ACTION_MOVE: {
                            mEventMove = true;
                        }
                        case MotionEvent.ACTION_UP: {
                            if (mEventDown && mEventMove) {
                                mEventMove = false;
                                mEventDown = false;
                                mEventUp = true;
                                slideListener.onPanelSlide();
                            }
                            break;
                        }
                        default:
                            v.performClick();
                    }
                    return true;
                }
        );

        params.gravity = gravity;
        mWindowManager.addView(view, params);
    }

    private void takeScreenShot() {
        execCommand(Commands.SCREEN_SHOT, TAKE_SCREEN_SHOT_TAG);
    }

    private void back() {
        execCommand(Commands.BACK, BACK_TAG);
    }

    private void home() {
        execCommand(Commands.HOME, HOME_TAG);
    }

    private void execCommand(String command, String commandTag) {
        Log.d(TAG, commandTag);

        RootUtils.execCommandByRoot(command, new ExecCommandCallback() {
            @Override
            public void onCommandDeny(@Nullable String info) {
                Log.d(TAG, "command deny " + info);
            }

            @Override
            public void onCommandAllow(@Nullable String info) {
                Log.d(TAG, "command allow " + info);
            }
        });
    }

}
