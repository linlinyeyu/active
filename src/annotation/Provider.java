package annotation;

import java.lang.annotation.*;

/**
 * Created by linlinyeyu on 2018/1/9.
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Provider {
    String value() default "QueueProvider";
    String topicName() default "";
}
