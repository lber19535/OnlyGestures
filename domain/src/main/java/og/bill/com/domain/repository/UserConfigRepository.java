package og.bill.com.domain.repository;

/**
 * Created by bill on 2018/2/7.
 */

public interface UserConfigRepository {

    void turnOnGesture();

    void turnOffGesture();

    boolean isGestureOff();
}
