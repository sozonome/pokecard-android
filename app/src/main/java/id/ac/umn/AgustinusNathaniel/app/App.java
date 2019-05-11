package id.ac.umn.AgustinusNathaniel.app;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class App extends Application {
    public static final String CHANNEL_1_ID = "registerData";

    @Override
    public void onCreate() {
        Log.d("Application Track", "Masuk App");
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel registerData = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Register Data",
                    NotificationManager.IMPORTANCE_HIGH
            );
            registerData.setDescription("This is Register Data");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(registerData);
        }
    }
}
