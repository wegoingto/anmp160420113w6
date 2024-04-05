package com.example.anmp_160420113_week4.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.anmp_160420113_week4.R
fun createNotificationChannel(
    context: Context,
    priority: Int,
    showBadge: Boolean,
    name: String,
    description: String,
) {
    val channelId = "${context.packageName} - ${context.getString(R.string.app_name)}"

    val channel = NotificationChannel(channelId, name, priority).apply {
        this.description = description
        setShowBadge(showBadge)
    }

    context.getSystemService(NotificationManager::class.java)
        .createNotificationChannel(channel)
}