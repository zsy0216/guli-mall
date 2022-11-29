package com.zsy.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义校验注解 声明可以取那些值
 * @author ZSY
 */
@Documented
// 可以指定多个校验器
@Constraint(validatedBy = {ListValueConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface ListValue {

    // 错误信息去哪里取
    String message() default "{com.zsy.common.valid.ListValue.message}";

    // 分组信息
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] values() default {};
}
