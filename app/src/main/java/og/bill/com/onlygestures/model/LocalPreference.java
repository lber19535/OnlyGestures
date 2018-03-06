package og.bill.com.onlygestures.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bill on 2018/1/19.
 */

public class LocalPreference {

    private static String LOCAL_PREFERENCE = "local_pref";
    private static String KEY_IS_GESTURE_OPEN = "gesture_open";

    public static boolean isGestureOpen(Context context) {
        SharedPreferences sp = context.getSharedPreferences(LOCAL_PREFERENCE, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_IS_GESTURE_OPEN, false);
    }

    public static void setGestureOpen(Context context, boolean opened) {
        SharedPreferences sp = context.getSharedPreferences(LOCAL_PREFERENCE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_IS_GESTURE_OPEN, opened).apply();
    }
}
