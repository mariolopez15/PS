package es.udc.psi.p21lopez;

import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    String ACTIVITY = "MainActiv";
    String TAG = "_TAG";
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, ACTIVITY+" onCreate"); //creamos un log

        button = (Button)findViewById(R.id.boton);
        button.setOnClickListener(this);

    }


    // Implement the OnClickListener callback
    @Override
    public void onClick(View v) {
        Log.d(TAG, ACTIVITY+" Clicked on "+ button.getText());
        button.setText(R.string.pulsado);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, ACTIVITY+" onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, ACTIVITY+" onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, ACTIVITY+" onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, ACTIVITY+" onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, ACTIVITY+" onPause");
    }
}