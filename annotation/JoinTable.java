package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * 多对多关系中使用到的注解
 */
public @interface JoinTable {

    /**
     * 目标第三方表表名
     *
     * @return
     */
    String tableName() default "";

    /**
     * 当前表中对应需要加入第三方表的字段
     *
     * @return
     */
    String ownColumnJoinTable() default "";

    /**
     * 多对多关系中另一张表需要加入第三方表的字段
     *
     * @return
     */
    String inverseColumnJoinTable() default "";
}
