package es.udc.psi.p26lopez;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Gestion de la emision
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
    }
}