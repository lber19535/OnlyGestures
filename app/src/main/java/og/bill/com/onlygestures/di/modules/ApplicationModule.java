package og.bill.com.onlygestures.di.modules;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bill on 2018/2/7.
 */

@Module
public class ApplicationModule {

    private Context appContext;

    public ApplicationModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Named("AppContext")
    public Context provideContext(){
        return appContext;
    }
}
