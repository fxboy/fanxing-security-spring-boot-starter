package live.fanxing.authentication.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllCalibrations {
    boolean permission() default true;
}
