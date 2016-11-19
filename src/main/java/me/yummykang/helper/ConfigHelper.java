package me.yummykang.helper;

import me.yummykang.constants.ConfigConstant;
import me.yummykang.utils.PropUtil;

import java.util.Properties;

/**
 * 配置文件helper类
 *
 * Created by Demon on 2016/11/19 0019.
 */
public class ConfigHelper {
    private static final Properties APP_CONFIG = PropUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getJdbcDriver() {
        return PropUtil.getString(APP_CONFIG, ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropUtil.getString(APP_CONFIG, ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUserName() {
        return PropUtil.getString(APP_CONFIG, ConfigConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return PropUtil.getString(APP_CONFIG, ConfigConstant.JDBC_PASSWORD);
    }

    public static String getBasePackage() {
        return PropUtil.getString(APP_CONFIG, ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getJspPath() {
        return PropUtil.getString(APP_CONFIG, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    public static String getAssetPath() {
        return PropUtil.getString(APP_CONFIG, ConfigConstant.APP_ASSET_PATH, "/asset");
    }
}
