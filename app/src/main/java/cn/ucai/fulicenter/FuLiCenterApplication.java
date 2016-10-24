package cn.ucai.fulicenter;

import android.app.Application;


public class FuLiCenterApplication extends Application {
    public static FuLiCenterApplication application;
    private static FuLiCenterApplication instance;
    private static String username;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        instance = this;

    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        FuLiCenterApplication.username = username;
    }

    public static FuLiCenterApplication getInstance(){
        if(instance==null){
            instance = new FuLiCenterApplication();
        }
        return instance;
    }
}
