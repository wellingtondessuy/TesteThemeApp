package com.example.themetest;

import java.util.Random;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private NotificationCompat.Builder mBuilder;
	private String KEY_PROFILE = "com.example.themetest.MainActivity.call";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createNotification();
	}

	public void createNotification() {
		//Gera um número aleatório para ser o número da notificação que será emitida
		Random rand = new Random();
		int notificationId = rand.nextInt(100 - 1) + 1;
		
		Intent intent = new Intent(this, ProfileActivity.class);
        PendingIntent pendingIntentCamera = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(KEY_PROFILE);
        // Add other actions as needed

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(KEY_PROFILE)) {
                    call();
                } 
            }
        };

        registerReceiver(receiver, filter);
        
        //Intent intenta = new Intent(this, ProfileActivity.class);
        //PendingIntent pendingIntentShare = PendingIntent.getBroadcast(this, 0, intenta, PendingIntent.FLAG_UPDATE_CURRENT);
        
        //Constrói a notificação
        mBuilder = new NotificationCompat.Builder(this)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle("Notification Test")
        .setContentText("Notification Text")
        .setNumber(notificationId)
        .setAutoCancel(true)
        .addAction(android.R.drawable.ic_menu_camera, "Camera", pendingIntentCamera)
        .setContentIntent(pendingIntentCamera);
 
        //Monta a notificação
        Notification n = mBuilder.build();
       
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Envia a notificação
        notificationManager.notify(notificationId, n);
	}
	
	
	
	public void call() {
		Toast.makeText(this, "No método call().", Toast.LENGTH_LONG).show();		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
