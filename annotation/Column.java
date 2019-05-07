package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 标识此字段为普通字段
 */
public @interface Column {
    /**
     * 表示当前实体对应数据库表中的字段的字段名，如果不写默认当前字段与数据库字段一致
     *
     * @return
     */
    String name() default "";
}
