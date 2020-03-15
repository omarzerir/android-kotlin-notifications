package com.example.android.eggtimernotifications

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.android.eggtimernotifications.util.createChannel
import com.example.android.eggtimernotifications.util.createPushChannel

class MyApp : Application(), Application.ActivityLifecycleCallbacks {

    companion object{
        var isOnBackground = true
    }
    private var numStarted = 0

    override fun onCreate() {
        super.onCreate()



        registerActivityLifecycleCallbacks(MyApp())
    }

    override fun onActivityStarted(activity: Activity?) {
        if (numStarted == 0) {
            // app went to foreground
            isOnBackground = false
        }
        numStarted++
    }

    override fun onActivityStopped(activity: Activity?) {
        numStarted--
        if (numStarted == 0) {
            // app went to background
            isOnBackground = true
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

}