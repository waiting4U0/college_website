package cn.edu.swpu.info.college_website.web.common;

import java.lang.annotation.*;

/**
 * @author LiShuangquan
 *2016/9/27
 */
@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Permission {
	String resourceKey() default "";
}
