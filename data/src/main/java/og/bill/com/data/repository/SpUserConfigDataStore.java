package og.bill.com.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.SoftReference;

import javax.inject.Named;

/**
 * Created by bill on 2018/2/8.
 */

public class SpUserConfigDataStore implements UserConfigDataStore{

    private SoftReference<SharedPreferences> mSp;
    private Context context;

    public SpUserConfigDataStore(@Named("AppContext") Context context) {
        this.context = context;
    }

    @Override
    public void setGestureState() {

    }

    @Override
    public boolean getGestureState() {
        return false;
    }
}
