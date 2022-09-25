package es.udc.psi.p22lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity implements View.OnClickListener {

    TextView texto;
    Button boton1;
    Button boton2;
    Button boton3;

    String KEY = "texto";
    String buttonKey = "boton";


    @Override
    public void onClick(View v){
        Intent intent = new Intent();
        //v.getId();
        switch(v.getId()){
            case R.id.button1:
                intent.putExtra(KEY, getResources().getString(R.string.boton1));
                break;

            case R.id.button2:
                intent.putExtra(KEY, getResources().getString(R.string.boton2));
                break;

            case R.id.button3:
                intent.putExtra(KEY, getResources().getString(R.string.boton3));

                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        //conectamos los botones al mismo listener
        Button boton1 = (Button)findViewById(R.id.button1);
        boton1.setOnClickListener(this);
        Button boton2 = (Button)findViewById(R.id.button2);
        boton2.setOnClickListener(this);
        Button boton3 = (Button)findViewById(R.id.button3);
        boton3.setOnClickListener(this);

        //obtenemos los datos del intent y actualizamos el texto
        texto=findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            texto.setText(bundle.getString(KEY, ""));
        }

    }


}