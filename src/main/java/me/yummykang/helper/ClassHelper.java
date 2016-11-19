package me.yummykang.helper;

import me.yummykang.annotation.Controller;
import me.yummykang.annotation.Service;
import me.yummykang.utils.ClassUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public class ClassHelper {
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> serviceClassSet = new HashSet<Class<?>>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Service.class)) {
                serviceClassSet.add(clazz);
            }
        }
        return serviceClassSet;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> controllerClassSet = new HashSet<Class<?>>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Controller.class)) {
                controllerClassSet.add(clazz);
            }
        }
        return controllerClassSet;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanSet = new HashSet<Class<?>>();
        beanSet.addAll(getServiceClassSet());
        beanSet.addAll(getControllerClassSet());
        return beanSet;
    }
}
