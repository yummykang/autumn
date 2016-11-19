package me.yummykang.bean;

import java.util.Map;

/**
 * write some dec. here.
 * Created by Demon on 2016/11/19 0019.
 */
public class View {

    private String path;

    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public void addModel(String key, Object value) {
        model.put(key, value);
    }
}
