package es.udc.psi.p26lopez;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {

    String TAG = "_TAG";
    public LocalService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        int count;
        try{
            count=Integer.parseInt(intent.getStringExtra("count"));
            Thread hilo=new CountThread(count);
            hilo.start();

            stopSelf();
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    class CountThread extends Thread {
        private final int count;
        CountThread(int count) {
            this.count = count;
        }
        public void run() {

            for(int i= count; i>=0; i--){
                try {
                    Log.d(TAG, "Cuenta nuemero: "+i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
            }


        }
    }
}