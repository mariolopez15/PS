package es.udc.psi.p26lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Objects;

import es.udc.psi.p26lopez.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    BroadcastReceiver br;
    BroadcastReceiver serviceReceiver;
    Intent local_service;
    Intent enlazado_service;
    EnlazadoService myBoundService;
    Boolean mBound = false;
    Thread tarea;
    String TAG = "_TAG";
    String EMISION_P= "es.udc.PSI.broadcast.GENERAL";
    String EMISION_L= "es.udc.PSI.broadcast.SWITCH_LOCAL";
    String EMISION_E= "es.udc.PSI.broadcast.SWITCH_ENLAZADO";

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            EnlazadoService.MyBinder binder = (EnlazadoService.MyBinder) service;
            myBoundService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Declaramos el recibidor de las emisiones
        br = new Receiver();
        serviceReceiver=new EndCountReceiver();

        IntentFilter filter_sistema = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //filter_sistema.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(br, filter_sistema); //Hacemos que sea sensible a esta emision


        Intent intent = new Intent();
        intent.setAction(EMISION_P);
        IntentFilter filter_propio = new IntentFilter(EMISION_P);
        registerReceiver(br, filter_propio);

        //Filtro para recibir emisiones del servicio local
        IntentFilter filter_local = new IntentFilter(EMISION_L);
        registerReceiver(serviceReceiver, filter_local);

        //Filtro para recibir emisiones del servicio enlazado
        IntentFilter filter_emision = new IntentFilter(EMISION_E);
        registerReceiver(serviceReceiver, filter_emision);





        binding.emision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al pulsar lanzar emision
                sendBroadcast(intent);
            }
        });


        binding.switchLocal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position

                if(isChecked){
                    if(!binding.cuenta.getText().toString().isEmpty()){
                        local_service = new Intent(getApplicationContext(), LocalService.class);
                        local_service.putExtra("count", binding.cuenta.getText().toString());
                        startService(local_service);
                    }else{
                        unCheckedLocal();
                    }

                }

                if(!isChecked){
                    //Paramos el servicio
                    stopService(local_service);
                    Log.d(TAG, "Switch servicio local off");
                }

            }
        });



        binding.switchEnlazado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position

                if(isChecked){
                    if(!binding.cuenta.getText().toString().isEmpty()){
                        //enlazado_service = new Intent(getApplicationContext(), EnlazadoService.class);
                        enlazado_service = new Intent(getApplicationContext(), EnlazadoService.class);
                        bindService(enlazado_service, connection, Context.BIND_AUTO_CREATE);
                        enlazado_service.putExtra("count", binding.cuenta.getText().toString());
                        startService(enlazado_service);
                    }else{
                        unCheckedEnlazado();
                    }

                }

                if(!isChecked){
                    //Paramos el servicio
                    stopService(enlazado_service);
                    unbindService(connection);
                    mBound = false;
                    Log.d(TAG, "Switch servicio en lazado off");
                }

            }
        });


        binding.botonValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al pulsar lanzar emision
                if(mBound){
                    Toast.makeText(getApplicationContext(), "Valor de la cuenta: " + myBoundService.getCount(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    if(!binding.cuenta.getText().toString().isEmpty()) {
                        myBoundService.restartCount(binding.cuenta.getText().toString());
                    }
                }
            }
        });


        binding.switchTarea.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position


                if(isChecked){
                    //iniciat thread
                    tarea=new Thread(CountRun);
                    tarea.start();
                    Log.d(TAG, "Inicia tarea");


                }

                if(!isChecked){
                    //Parar thread
                    tarea.interrupt();
                    binding.textoTarea.setText("");
                    Log.d(TAG, "Tarea parada");
                }

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //ponemos off el switch y por lo tanto se pone a false mBound y se hace el unbindService
        unCheckedEnlazado();
        Log.d(TAG, "Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(binding.switchLocal.isChecked()){
            unCheckedLocal();
        }
        if(binding.switchEnlazado.isChecked()){
            unCheckedEnlazado();
        }
        unregisterReceiver(br);
        unregisterReceiver(serviceReceiver);
        Log.d(TAG, "Destroy");
    }

    public void unCheckedLocal(){
        binding.switchLocal.setChecked(false);
    }

    public void unCheckedEnlazado(){
        binding.switchEnlazado.setChecked(false);
    }




    public class EndCountReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(Objects.equals(intent.getAction(), EMISION_L)){
                Log.d(TAG, "Cuenta del servicio local terminada");
                unCheckedLocal();
            }

            if(Objects.equals(intent.getAction(), EMISION_E)){
                Log.d(TAG, "Cuenta del servicio enlazado terminada");
                unCheckedEnlazado();
            }

        }
    }

    Runnable CountRun = new Runnable() {
        @Override
        public void run() {
            while(true){
                if(mBound){
                    try {
                        showData(String.valueOf(getCount()));
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

            }
        }

        private void showData (String data) {
            binding.textoTarea.post(new Runnable() {
                @Override
                public void run() {
                    binding.textoTarea.setText(data);
                }
            });
        }

        public int getCount(){
            return myBoundService.getCount();
        }


    };

    /*

    class TareaThread extends Thread {
        TareaThread() {
        }
        public void run() {

            while(true){
                try {
                    showData(String.valueOf(getCount()));
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }


        }

        private void showData (String data) {
            binding.textoTarea.post(new Runnable() {
                @Override
                public void run() {
                    binding.textoTarea.setText(data);
                }
            });
        }

        public int getCount(){
            return myBoundService.getCount();
        }
    }


     */




}