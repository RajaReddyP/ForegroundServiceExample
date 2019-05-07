package com.polamrapps.foregroundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Objects;

public class ForegroundService extends Service {

    private static final String TAG = "ForegroundService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        switch (Objects.requireNonNull(intent.getAction())) {
            case Constants.ACTION.STARTFOREGROUND_ACTION:
                Log.i(TAG, "onStartCommand: STARTFOREGROUND_ACTION");
                Intent notifyIntent = new Intent(this, MainActivity.class);
                notifyIntent.setAction(Constants.ACTION.MAIN_ACTION);
                notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, 0);

                Intent previousIntent = new Intent(this, ForegroundService.class);
                previousIntent.setAction(Constants.ACTION.PREV_ACTION);
                PendingIntent pPreviousIntent = PendingIntent.getService(this, 0, previousIntent, 0);

                Intent playIntent = new Intent(this, ForegroundService.class);
                playIntent.setAction(Constants.ACTION.PLAY_ACTION);
                PendingIntent pPlayIntent = PendingIntent.getService(this, 0, playIntent, 0);

                Intent nextIntent = new Intent(this, ForegroundService.class);
                nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
                PendingIntent pNextIntent = PendingIntent.getService(this, 0, nextIntent, 0);

                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo_48);
                String channelId = "";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    channelId = createNotificationChannel();
                }
                Notification notification = new NotificationCompat.Builder(this, channelId).setContentTitle("Tutorial Music Player").setTicker("Music Player").setContentText("My Music").setSmallIcon(R.drawable.logo_48).setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false)).setContentIntent(pendingIntent).setOngoing(true).addAction(android.R.drawable.ic_media_previous, "Previous", pPreviousIntent).addAction(android.R.drawable.ic_media_play, "Play", pPlayIntent).addAction(android.R.drawable.ic_media_next, "Next", pNextIntent).build();
                startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, notification);
                break;
            case Constants.ACTION.PREV_ACTION:
                Log.i(TAG, "onStartCommand: PREV_ACTION");
                break;
            case Constants.ACTION.PLAY_ACTION:
                Log.i(TAG, "onStartCommand: PLAY_ACTION");
                break;
            case Constants.ACTION.NEXT_ACTION:
                Log.i(TAG, "onStartCommand: NEXT_ACTION");
                break;
            case Constants.ACTION.STOPFOREGROUND_ACTION:
                Log.i(TAG, "onStartCommand: STOPFOREGROUND_ACTION");
                stopForeground(true);
                stopSelf();
                break;
        }

        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        NotificationChannel cha = new NotificationChannel("MyService", "My Background Service",
                NotificationManager.IMPORTANCE_NONE);
        cha.setLightColor(Color.BLUE);
        cha.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (service != null) {
            service.createNotificationChannel(cha);
        }
        return "MyService";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
