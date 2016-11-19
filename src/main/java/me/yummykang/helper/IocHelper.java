package me.yummykang.helper;

import me.yummykang.annotation.Inject;
import me.yummykang.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (beanMap != null && beanMap.size() != 0) {
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                Class<?> clazz = entry.getKey();
                Object obj = entry.getValue();
                Field[] fields = clazz.getDeclaredFields();
                if (fields != null) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            Class<?> fieldType = field.getType();
                            Object beanFieldInstance = beanMap.get(fieldType);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(obj, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
