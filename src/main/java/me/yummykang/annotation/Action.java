package me.yummykang.annotation;

import me.yummykang.constants.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String value();

    RequestMethod method();
}
