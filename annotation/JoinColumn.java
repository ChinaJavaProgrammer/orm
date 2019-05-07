package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JoinColumn {
    /**
     * 一对多或者多对一关系中自己作为主键或者外键的字段
     *
     * @return
     */
    String ownColumn() default "";

    /**
     * 一对多或者多对一关系中约束到对应表的字段
     *
     * @return
     */
    String targetColumn() default "";
}
