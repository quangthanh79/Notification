package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnClick;
    Button btnClick2;
    private final static String CHANNEL_ID_1 = "NOTIFICACION1";
    private final static String CHANNEL_ID_2 = "NOTIFICACION1";

    public final static int NOTIFICACION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClick  = findViewById(R.id.button);
        btnClick2 = findViewById(R.id.button2);
        createNotificationChannel();
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification();
            }
        });
        btnClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification2();
            }
        });

    }
    private int getNotificationId(){
        return (int) new Date().getTime();
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            AudioAttributes attributes = new AudioAttributes.Builder()
                                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                                            .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //config chanel 1
            CharSequence name1 = "chanel 1";
            NotificationChannel notificationChannel1 = new NotificationChannel(CHANNEL_ID_1, name1, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel1);
            notificationChannel1.setSound(uri,attributes);
            //config chanel 2
            CharSequence name2 = "chanel 2";
            NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID_2, name2, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel2);
            notificationChannel2.setSound(uri,attributes);
        }
    }
    private void createNotification(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_1);
        builder.setSmallIcon(R.drawable.ic_baseline_message_24);
        builder.setContentTitle("Cảnh báo");
        builder.setContentText("Bạn đang chi vượt mức tháng 12/2021!");
        builder.setColor(Color.RED);
        builder.setSound(uri);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(getNotificationId(), builder.build());
    }

    private void createNotification2(){
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_2);
        builder.setSmallIcon(R.drawable.ic_baseline_message_24);
        builder.setContentTitle("Cảnh báo");
        builder.setContentText("Bạn đang chi vượt mức tháng 01/2022!");
        builder.setColor(Color.GREEN);
        builder.setSound(alarmSound);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(getNotificationId(), builder.build());
    }
}