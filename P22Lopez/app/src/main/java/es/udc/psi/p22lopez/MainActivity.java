package es.udc.psi.p22lopez;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editedText;
    String KEY = "texto";

    //definimos los listeners
    private View.OnClickListener compartirListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editedText = (EditText)findViewById(R.id.campoEditable);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            String s = editedText.getText().toString();
            Log.d("TAG_", s);
            sendIntent.putExtra(Intent.EXTRA_TEXT, s);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

        }
    };

    private View.OnClickListener continuarListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editedText = (EditText)findViewById(R.id.campoEditable);

            if(!TextUtils.isEmpty(editedText.getText().toString())){
                createIntentContinuar();
            }else{
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.textoVacio), Toast.LENGTH_SHORT).show();
            }

            /*si ponemos aqui el codigo con referencia a la otra actividad no la va a encontrar
            * por que se encuenytra fuera del espacio, por eso llamamos a una función*/
        }
    };



    public void createIntentContinuar(){
        Intent continuarIntent = new Intent(this, Actividad2.class);
        continuarIntent.putExtra(KEY, editedText.getText().toString());
        my_startActivityForResult.launch(continuarIntent);
    }

    ActivityResultLauncher<Intent> my_startActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    if (bundle!= null) {
                        String text = bundle.getString(KEY, editedText.getText().toString());
                        editedText.setText(text);
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos los botones y los enlazamos con los listeners
        Button compartir = (Button)findViewById(R.id.compartir);
        compartir.setOnClickListener(compartirListener);

        Button continuaur = (Button)findViewById(R.id.continuar);
        continuaur.setOnClickListener(continuarListener);
    }
}