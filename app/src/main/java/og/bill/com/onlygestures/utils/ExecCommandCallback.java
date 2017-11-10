package og.bill.com.onlygestures.utils;

import android.support.annotation.Nullable;

/**
 * Created by bill on 2017/11/1.
 */

public interface ExecCommandCallback {
    void onCommandDeny(@Nullable String info);

    void onCommandAllow(@Nullable String info);
}
