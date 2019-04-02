package cn.edu.swpu.info.college_website.web.common;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Permission {
	String resourceKey() default "";
}
