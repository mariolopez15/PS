package es.udc.psi.p22lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {

    TextView texto;
    String KEY = "texto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        texto=findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            texto.setText(bundle.getString(KEY, ""));
        }

    }
}