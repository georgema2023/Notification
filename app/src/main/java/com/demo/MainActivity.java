package com.demo;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1001, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Context context = getApplicationContext();
        String channelId = "ChannelId"; // 通知渠道

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(NOTIFICATION_SERVICE);

        Notification notification = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容")
                    .setContentIntent(pendingIntent)
                    .build();
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "通知的渠道名称",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);
        } else {
            notification = new Notification.Builder(this)
                    .setContentTitle("通知标题")
                    .setContentText("通知内容")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .build();
        }

        Log.e("TAG", "onCreate: notify");

        notificationManager.notify(id, notification);

    }

}
