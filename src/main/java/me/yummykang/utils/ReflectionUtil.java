package me.yummykang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> clazz) {
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            logger.error("new instance failed", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            logger.error("new instance failed", e);
            throw new RuntimeException(e);
        }

        return instance;
    }

    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        method.setAccessible(true);
        try {
            result = method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            logger.error("invoke method failed", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            logger.error("invoke method failed", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    public static void setField(Object obj, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error("set field failed", e);
            throw new RuntimeException(e);
        }
    }
}
