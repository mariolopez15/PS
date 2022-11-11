package es.udc.psi.p26lopez;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class EnlazadoService extends Service {

    private final IBinder binder = new MyBinder();
    String TAG = "_TAG";
    String EMISION_E= "es.udc.PSI.broadcast.SWITCH_ENLAZADO";
    Intent intent_enlazado;
    CountThread hilo;

    public class MyBinder extends Binder {
        EnlazadoService getService() {
            // Return this instance of LocalService so clients can call public methods
            return EnlazadoService.this;
        }
    }

    public EnlazadoService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        int count;


        intent_enlazado = new Intent();
        intent_enlazado.setAction(EMISION_E);


        try{
            count=Integer.parseInt(intent.getStringExtra("count"));
            hilo=new EnlazadoService.CountThread(count);
            hilo.start();


        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        Log.d(TAG, "Destruido servicio enlazado");
        hilo.interrupt();
    }

    int getCount(){
        return hilo.getCount();
    }

    void restartCount(String count){
        try{
            int value =Integer.parseInt(count);
            hilo.interrupt();
            hilo=new EnlazadoService.CountThread(value);
            hilo.start();


        }catch (NumberFormatException e){
            e.printStackTrace();
        }

    }


    class CountThread extends Thread {
        private int count;
        CountThread(int count) {
            this.count = count;
        }
        public void run() {

            for(int i= count; i>=0; i--){
                count--;
                try {
                    Log.d(TAG, "Cuenta servicio enlazado nuemero: "+i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            sendBroadcast(intent_enlazado);


        }

        public int getCount(){
            return count;
        }
    }
}