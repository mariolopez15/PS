package es.udc.psi.p25lopez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    int notificationId=53234;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        createNotificationChannel(); //creamos el canal

        lista= findViewById(R.id.lista);

        String[] data = {getString(R.string.uno), getString(R.string.dos), getString(R.string.tres),
                getString(R.string.cuatro), getString(R.string.cinco), getString(R.string.seis), getString(R.string.siete)};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lista.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
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
                return true;
            case R.id.realizar:
                Toast.makeText(this, R.string.tarea_toast, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "La tarea ha sido realizada");
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("My intent notification")
                .setContentText("Description of the intent")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, builder.build());


        //CREAR UN INTENT PARA CADA BOTON DE LA NOTIFICACION
    }

}