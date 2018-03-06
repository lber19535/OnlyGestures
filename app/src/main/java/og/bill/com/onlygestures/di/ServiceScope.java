package og.bill.com.onlygestures.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by bill on 2018/2/7.
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ServiceScope {
}
