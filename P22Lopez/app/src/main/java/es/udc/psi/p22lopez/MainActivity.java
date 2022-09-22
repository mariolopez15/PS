package es.udc.psi.p22lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editedText;
    String KEY = "texto";


    private View.OnClickListener compartirListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editedText = (EditText)findViewById(R.id.campoEditable);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(KEY, editedText.getText());
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

        }
    };

    private View.OnClickListener continuarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createIntentContinuar();
            /*si ponemos aqui el codigo con referencia a la otra actividad no la va a encontrar
            * por que se encuenytra fuera del espacio, por eso llamamos a una función*/
        }
    };

    public void createIntentContinuar(){
        editedText = (EditText)findViewById(R.id.campoEditable);
        Intent continuarIntent = new Intent(this, Actividad2.class);
        continuarIntent.putExtra(KEY, editedText.getText().toString());
        startActivity(continuarIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button compartir = (Button)findViewById(R.id.compartir);
        compartir.setOnClickListener(compartirListener);

        Button continuaur = (Button)findViewById(R.id.continuar);
        continuaur.setOnClickListener(continuarListener);
    }
}