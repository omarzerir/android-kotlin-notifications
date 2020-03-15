/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.util

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.MyApp
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */

fun fireNotification(messageBody: String, applicationContext: Context){
    //if (MyApp.isOnBackground) {
    val notificationManager = ContextCompat.getSystemService(
        applicationContext,
        NotificationManager::class.java
    ) as NotificationManager
    notificationManager.sendNotification(messageBody, applicationContext)
    // }
}

fun clearNotification(applicationContext: Context){
    val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
    notificationManager.cancelAll()
}

private fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val pendingIntent = createPendingIntent(applicationContext)
    val style = createNotificationStyle(applicationContext)
    val image = createBigImage(applicationContext)
    val snoozePendingIntent = createSnoozePendingIntent(applicationContext)

    val builder = NotificationCompat.Builder(applicationContext, applicationContext.getString(R.string.egg_notification_channel_id))
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setStyle(style)
        .setLargeIcon(image)
        .setDefaults(Notification.DEFAULT_ALL)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .addAction(R.drawable.egg_icon, applicationContext.getString(R.string.snooze), snoozePendingIntent)

    notify(NOTIFICATION_ID, builder.build())
}

fun createChannel(applicationContext: Context){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val notificationChannel = NotificationChannel(applicationContext.getString(R.string.egg_notification_channel_id),
            applicationContext.getString(R.string.egg_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Time for Breakfast"
        notificationChannel.setShowBadge(false)
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

fun createPushChannel(applicationContext: Context){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val notificationChannel = NotificationChannel(applicationContext.getString(R.string.breakfast_notification_channel_id),
            applicationContext.getString(R.string.breakfast_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Time for Breakfast"
        notificationChannel.setShowBadge(false)
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

private fun createPendingIntent(applicationContext: Context): PendingIntent {
    val contentIntent = Intent(applicationContext,  MainActivity::class.java)
    contentIntent.putExtra("String", "try")
    return PendingIntent.getActivity(applicationContext, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)
}

private fun createBigImage(applicationContext: Context): Bitmap {
    return BitmapFactory.decodeResource(applicationContext.resources, R.drawable.cooked_egg)
}

private fun createNotificationStyle(applicationContext: Context): NotificationCompat.BigPictureStyle {
    return NotificationCompat.BigPictureStyle()
        .bigPicture(createBigImage(applicationContext))
        .bigLargeIcon(null)
}

private fun createSnoozePendingIntent(applicationContext: Context): PendingIntent{
    val contentIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    return PendingIntent.getBroadcast(applicationContext, REQUEST_CODE, contentIntent, FLAGS)
}
