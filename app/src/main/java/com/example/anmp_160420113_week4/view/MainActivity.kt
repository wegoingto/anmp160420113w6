package com.example.anmp_160420113_week4.view

import android.Manifest
import android.app.NotificationManager
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.anmp_160420113_week4.R
import com.example.anmp_160420113_week4.util.createNotificationChannel

class MainActivity : AppCompatActivity() {
    companion object {
        private var instance: MainActivity? = null

        fun postNotification(
            title: String,
            content: String,
            @DrawableRes icon: Int,
        ) {
            val channelId = "${instance!!.packageName} - ${instance!!.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!, channelId).apply {
                priority = NotificationCompat.PRIORITY_DEFAULT
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                setAutoCancel(true)
            }

            val notificationManager = NotificationManagerCompat.from(instance!!)

            if (
                ContextCompat.checkSelfPermission(
                    instance!!, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }

            notificationManager.notify(1001, notificationBuilder.build())
        }
    }
    init {
        instance = this
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(
            this,
            NotificationManager.IMPORTANCE_DEFAULT,
            false,
            getString(R.string.app_name),
            "Notification channel for creating new student",
        )
    }
}