package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 多对一注解
 */
public @interface ManyToOne {
    /**
     * 目标表的实体类
     *
     * @return
     */
    Class targer() default Object.class;
}
