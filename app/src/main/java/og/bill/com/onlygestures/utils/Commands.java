package og.bill.com.onlygestures.utils;

import android.view.KeyEvent;

/**
 * Created by bill on 2017/11/1.
 */

public class Commands {
    public static final String SCREEN_SHOT = "input keyevent " + KeyEvent.KEYCODE_SYSRQ + "\n";
    public static final String HOME = "input keyevent " + KeyEvent.KEYCODE_HOME + "\n";
    public static final String BACK = "input keyevent " + KeyEvent.KEYCODE_BACK + "\n";
}
