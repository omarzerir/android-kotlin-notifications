package com.example.android.eggtimernotifications

import android.util.Log
import com.example.android.eggtimernotifications.util.fireNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage?) {

        Log.e("FIREBASE TAG", "FROM: ${p0?.from}")

        p0?.data?.let {
            Log.e("FIREBASE TAG", "DATA: ${p0.data}")
        }

        p0?.notification?.let {
            Log.e("FIREBASE TAG", "BODY: ${it.body}")
            fireNotification(it.body!!, applicationContext)
        }
    }

    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
        Log.e("FIREBASE TAG", p0)
    }

}