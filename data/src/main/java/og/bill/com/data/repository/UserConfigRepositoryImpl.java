package og.bill.com.data.repository;

import og.bill.com.domain.repository.UserConfigRepository;

/**
 * Created by bill on 2018/2/7.
 */

public class UserConfigRepositoryImpl implements UserConfigRepository {

    private UserConfigDataStore mUserConfigDataStore;

    public UserConfigRepositoryImpl(UserConfigDataStore mUserConfigDataStore) {
        this.mUserConfigDataStore = mUserConfigDataStore;
    }

    @Override
    public void turnOnGesture() {

    }

    @Override
    public void turnOffGesture() {

    }

    @Override
    public boolean isGestureOff() {
        return false;
    }
}
