package com.common.common;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by sun on 16/3/11.
 */
public class SerializableMap {

    /**
     *
     */
    private static final long serialVersionUID = -1806894805605142721L;

    private static Map<String, String> map;

    static {
        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Authorization", "Basic " + LoginDataCenterManager.getInstance().getToken());
//        headers.put("Authorization", "Basic ");
//        headers.put("xingyunFromApp", "Android");
        map = headers;
    }

    private volatile static SerializableMap mInstance;

    public static SerializableMap getInstance() {
        if (mInstance == null) {
            synchronized (SerializableMap.class) {
                if (mInstance == null) {
                    mInstance = new SerializableMap();
                }
            }
        }
        return mInstance;
    }

    private SerializableMap() {

    }


    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


}
