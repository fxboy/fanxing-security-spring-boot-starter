package live.fanxing.authentication.annotation;

import live.fanxing.authentication.enums.Method;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasAuthRole {
    String[] roles();
    String[] permissions() default {};
    String[] method() default "AUTO";
}