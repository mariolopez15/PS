package es.udc.psi.p23lopez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements TopFragment.OnButtonSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TopFragment

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager_top = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction_top = fragmentManager_top.beginTransaction();
        TopFragment top = new TopFragment();
        fragmentTransaction_top.replace(R.id.top_fragment, top, "TOP_F");
        fragmentTransaction_top.commit();



        //DownFragment
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager_down = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction_down = fragmentManager_down.beginTransaction();
        DownFragment down = new DownFragment();
        fragmentTransaction_down.replace(R.id.down_fragment, down, "DOWN_F");
        fragmentTransaction_down.commit();
    }

    public void onButton_AB_Selected(Character letra){
        Log.d("TAG_", "boton: " + letra);
        DownFragment down = (DownFragment) getSupportFragmentManager()
                .findFragmentByTag("DOWN_F");
        down.actulizarTexto(letra);
    }
    public void onButton_C_Selected(String url){
        Log.d("TAG_", "boton C  ->  url: " + url );
        DownFragment down = (DownFragment) getSupportFragmentManager()
                .findFragmentByTag("DOWN_F");
        down.actualizarTexto(url);
        down.actualizarNavegador(url);

    }
}