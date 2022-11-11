package es.udc.psi.p26lopez;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {

    String TAG = "_TAG";
    String EMISION_L= "es.udc.PSI.broadcast.SWITCH_LOCAL";
    Intent intent_local;
    Thread hilo;
    public LocalService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        int count;


        intent_local = new Intent();
        intent_local.setAction(EMISION_L);


        try{
            count=Integer.parseInt(intent.getStringExtra("count"));
            hilo=new CountThread(count);
            hilo.start();


        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        Log.d(TAG, "Destruido");
        hilo.interrupt();
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
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            sendBroadcast(intent_local);


        }
    }
}