package com.example.powerfailure;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import io.fabric.sdk.android.Fabric;


/** * Created by Paulo Vila Nova on 13/06/2017.
 */

public class PowerFailureApp extends Application {

    private static PowerFailureApp singleton;
    private   Bus bus;

    public static PowerFailureApp getInstance() {
        return singleton;
    };

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
        init();
//        //Creating an Exception
//        if(singleton != null){
//            throw new IllegalArgumentException("Crash verified");
//        }




    }

    private void init() {
        singleton = this;
        bus = new Bus(ThreadEnforcer.ANY, PowerFailureApp.class.getSimpleName());
    }

    public void register(Object object) {
        bus.register(object);
    }

    public void unregister(Object object) {
        bus.unregister(object);
    }

    public void post(Object event) {
        bus.post(event);
    }
}
