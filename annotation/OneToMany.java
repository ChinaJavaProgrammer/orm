package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 一对多关系注解
 */
public @interface OneToMany {

    /**
     * 目标的表的实体类
     *
     * @return
     */
    Class targer() default Object.class;
}
