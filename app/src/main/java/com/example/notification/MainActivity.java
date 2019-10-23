package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText title,text;
    Button notifyButton;
    private static String channel_id = "channel1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (EditText) findViewById(R.id.editText);
        text = (EditText) findViewById(R.id.editText2);
        notifyButton = (Button) findViewById(R.id.button);
        createNotificationChannel();
    }
    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channel_id, "channel",
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    public void onNotify(View view){
        String tit = title.getText().toString();
        String message = text.getText().toString();
        Notification newMessageNotification = new NotificationCompat.Builder(this, channel_id)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(tit).setContentText(message).build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, newMessageNotification);
    }
}
