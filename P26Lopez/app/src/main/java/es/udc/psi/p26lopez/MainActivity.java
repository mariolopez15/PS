package es.udc.psi.p26lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import es.udc.psi.p26lopez.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    BroadcastReceiver br;
    String EMISION_P= "es.udc.PSI.broadcast.GENERAL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Declaramos el recibidor de las emisiones
        br = new Receiver();

        IntentFilter filter_sistema = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //filter_sistema.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(br, filter_sistema); //Hacemos que sea sensible a esta emision


        Intent intent = new Intent();
        intent.setAction(EMISION_P);
        IntentFilter filter_propio = new IntentFilter(EMISION_P);
        registerReceiver(br, filter_propio);


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
                    //lanzar servicio
                    Intent service = new Intent(getApplicationContext(), LocalService.class);
                    if(!binding.cuentaLocal.getText().toString().isEmpty()){
                        service.putExtra("count", binding.cuentaLocal.getText().toString());
                        startService(service);
                    }

                }

                if(!isChecked){
                    //Paramos el servicio

                }

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}