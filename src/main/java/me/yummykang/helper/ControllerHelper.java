package me.yummykang.helper;

import me.yummykang.annotation.Action;
import me.yummykang.bean.Handler;
import me.yummykang.bean.Request;
import me.yummykang.constants.RequestMethod;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> classSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(classSet)) {
            for (Class<?> clazz : classSet) {
                Method[] methods = clazz.getDeclaredMethods();
                if (methods != null && methods.length != 0) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            RequestMethod requestMethod = action.method();
                            Request request = new Request(requestMethod.getMethod(), mapping);
                            Handler handler = new Handler(clazz, method);
                            ACTION_MAP.put(request, handler);
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(Request request) {
        return ACTION_MAP.get(request);
    }
}
