package es.udc.psi.p23lopez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle(R.string.TituloDialogo);
        dialogo.setMessage(R.string.MensajeDialogo);

        dialogo.setPositiveButton(getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialogOK();
            }
        });
        dialogo.setNegativeButton(getResources().getString(R.string.Cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialogCancel();
            }
        });
        AlertDialog alert = dialogo.create();
        alert.show();


    }

    private void dialogOK(){
        Log.d("_TAG", "Dialogo atras aceptado");
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.OK), Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
    private void dialogCancel(){
        Log.d("_TAG", "Dialogo atras cancelado");
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.Cancelar), Toast.LENGTH_SHORT).show();
    }

    public void onButton_AB_Selected(Character letra){
        DownFragment down = (DownFragment) getSupportFragmentManager()
                .findFragmentByTag("DOWN_F");
        down.actulizarTexto(letra);
    }
    public void onButton_C_Selected(String url){
        DownFragment down = (DownFragment) getSupportFragmentManager()
                .findFragmentByTag("DOWN_F");
        down.actualizarTexto(url);
        down.actualizarNavegador(url);

    }
}