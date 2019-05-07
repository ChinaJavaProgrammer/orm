package annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 标识对应数据库表的注解
 */
public @interface Table {

    /**
     * 当前实体类对应数据库的表名如果不写默认数据库表名和实体类的类名一致
     *
     * @return
     */
    String name() default "";
}
