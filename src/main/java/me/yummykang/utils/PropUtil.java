package me.yummykang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public class PropUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropUtil.class);

    /**
     * 载入properties文件
     *
     * @param fileName 文件名
     * @return java.util.Properties
     */
    public static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + "file is not found.");
            }
            properties = new Properties();
            properties.load(is);
        } catch (FileNotFoundException e) {
            logger.error("can not find the specific properties file", e);
        } catch (IOException e) {
            logger.error("load properties file failed.", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("close inputstream failed.", e);
                }
            }
        }
        return properties;
    }

    /**
     * 根据key获取string类型的value
     *
     * @param properties java.util.Properties
     * @param key 键
     * @return java.lang.String
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 根据key获取string类型的value
     *
     * @param properties java.util.Properties
     * @param key 键
     * @param defaultValue 如果key对应的值为空，则返回的默认值
     * @return java.lang.String
     */
    public static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }
}
