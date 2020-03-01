package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 15:29 2020/3/1
 *@Modified By:
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowire {

    String value() default "";

}
