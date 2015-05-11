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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private NotificationCompat.Builder mBuilder;
	private String KEY_PROFILE = "com.example.themetest.MainActivity.call";
	private ImageButton btnAdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		btnAdd = (ImageButton) findViewById(R.id.add_button);
		btnAdd.setOnClickListener(this);
		//createNotification();
	}

	public void createNotification() {
		//Gera um n�mero aleat�rio para ser o n�mero da notifica��o que ser� emitida
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
        
        //Constr�i a notifica��o
        mBuilder = new NotificationCompat.Builder(this)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle("Notification Test")
        .setContentText("Notification Text")
        .setNumber(notificationId)
        .setAutoCancel(true)
        .addAction(android.R.drawable.ic_menu_camera, "Camera", pendingIntentCamera)
        .setContentIntent(pendingIntentCamera);
 
        //Monta a notifica��o
        Notification n = mBuilder.build();
       
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //Envia a notifica��o
        notificationManager.notify(notificationId, n);
	}
	
	public void call() {
		Toast.makeText(this, "No m�todo call().", Toast.LENGTH_LONG).show();		
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

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.add_button:
				Intent intent = new Intent(this, ProfileActivity.class);
				startActivity(intent);
				finish();
			break;
		}	
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
	}
}