package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 表示多对多关系的注解
 */
public @interface ManyToMany {
    /**
     * 目标多对多实体
     *
     * @return
     */
    Class targer() default Object.class;
}
