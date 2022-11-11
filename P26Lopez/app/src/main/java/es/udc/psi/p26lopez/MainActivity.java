package es.udc.psi.p26lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import es.udc.psi.p26lopez.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    BroadcastReceiver br;
    BroadcastReceiver localReceiver;
    Intent local_service;
    String TAG = "_TAG";
    String EMISION_P= "es.udc.PSI.broadcast.GENERAL";
    String EMISION_L= "es.udc.PSI.broadcast.SWITCH_LOCAL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Declaramos el recibidor de las emisiones
        br = new Receiver();
        localReceiver=new EndCountReceiver();

        IntentFilter filter_sistema = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //filter_sistema.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(br, filter_sistema); //Hacemos que sea sensible a esta emision


        Intent intent = new Intent();
        intent.setAction(EMISION_P);
        IntentFilter filter_propio = new IntentFilter(EMISION_P);
        registerReceiver(br, filter_propio);


        IntentFilter filter_local = new IntentFilter(EMISION_L);
        registerReceiver(localReceiver, filter_local);



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
                    if(!binding.cuentaLocal.getText().toString().isEmpty()){
                        local_service = new Intent(getApplicationContext(), LocalService.class);
                        local_service.putExtra("count", binding.cuentaLocal.getText().toString());
                        startService(local_service);
                    }else{
                        unCheckedLocal();
                    }

                }

                if(!isChecked){
                    //Paramos el servicio
                    stopService(local_service);
                    Log.d(TAG, "Switch off");
                }

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
        unregisterReceiver(localReceiver);
    }

    public void unCheckedLocal(){
        binding.switchLocal.setChecked(false);
    }




    public class EndCountReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Cuenta terminada");
            unCheckedLocal();

        }
    }


}