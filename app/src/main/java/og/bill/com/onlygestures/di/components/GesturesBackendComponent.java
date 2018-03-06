package og.bill.com.onlygestures.di.components;

import dagger.Component;
import og.bill.com.onlygestures.di.ServiceScope;
import og.bill.com.onlygestures.di.modules.ApplicationModule;

/**
 * Created by bill on 2018/2/7.
 */

@ServiceScope
@Component(dependencies = ApplicationComponent.class, modules = {ApplicationModule.class})
public interface GesturesBackendComponent {
}
