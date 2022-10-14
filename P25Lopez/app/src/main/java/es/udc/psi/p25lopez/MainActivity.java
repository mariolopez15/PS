package es.udc.psi.p25lopez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    ListView lista;
    String TAG = "_TAG";
    String CHANNEL_ID="id_canal_app";
    String ACCEDER_ID = "acceder_id_extra";
    String EDITAR_ID = "editar_id_extra";
    int notificationId=53234;
    NotificationManagerCompat notificationManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        createNotificationChannel(); //creamos el canal
        notificationManager = NotificationManagerCompat.from(this);

        lista= findViewById(R.id.lista);

        String[] data = {getString(R.string.uno), getString(R.string.dos), getString(R.string.tres),
                getString(R.string.cuatro), getString(R.string.cinco), getString(R.string.seis), getString(R.string.siete)};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorito:
                createSnack();
                return true;
            case R.id.lanzar:
                crearNotificacion();
                return true;
            case R.id.borrar:
               notificationManager.cancel(notificationId);
                return true;
            case R.id.realizar:
                Toast.makeText(this, R.string.tarea_toast, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "La tarea ha sido realizada");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.editar:
                Log.d(TAG, "menu: "+ item.getTitle().toString() +" item: "+ info.id);
                return true;
            case R.id.compartir:
                Log.d(TAG, "menu: "+ item.getTitle().toString() +" item: "+ info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void createSnack(){
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.linear), R.string.favorito_msg, Snackbar.LENGTH_LONG);
        mySnackbar.setAction(R.string.deshacer_botton, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Se ha deshecho la operacion favorito");
            }
        });
        mySnackbar.show();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal_app";
            String description = "Canal para notificaion de la app";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    name, importance);
            channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
            NotificationManager notifManager = getSystemService(NotificationManager.class);
            notifManager.createNotificationChannel(channel);
        }
    }

    void crearNotificacion(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Intent accederIntent = new Intent(this, MainActivity.class);
        accederIntent.setAction("Acceder");
        accederIntent.putExtra(ACCEDER_ID, 0);
        PendingIntent accederPendingIntent =
                PendingIntent.getBroadcast(this, 0, accederIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        Intent editarIntent = new Intent(this, MainActivity.class);
        editarIntent.setAction("Editar");
        editarIntent.putExtra(EDITAR_ID, 0);
        PendingIntent editarPendingIntent =
                PendingIntent.getBroadcast(this, 0, editarIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.titulo_notificacion))
                .setContentText(getString(R.string.texto_notificacion))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, getString(R.string.acceder_boton),
                        accederPendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, getString(R.string.editar_boton),
                    editarPendingIntent);


        notificationManager.notify(notificationId, builder.build());


    }

}