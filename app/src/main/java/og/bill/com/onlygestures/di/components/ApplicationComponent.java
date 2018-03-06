package og.bill.com.onlygestures.di.components;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import og.bill.com.onlygestures.di.modules.ApplicationModule;

/**
 * Created by bill on 2018/2/7.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application application);

    Context context();

}
