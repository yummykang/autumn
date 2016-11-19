package me.yummykang.bean;

import java.lang.reflect.Method;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public class Handler {
    private Class<?> contrllerClass;

    private Method actionMethod;

    public Handler(Class<?> contrllerClass, Method actionMethod) {
        this.contrllerClass = contrllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getContrllerClass() {
        return contrllerClass;
    }

    public void setContrllerClass(Class<?> contrllerClass) {
        this.contrllerClass = contrllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }
}
