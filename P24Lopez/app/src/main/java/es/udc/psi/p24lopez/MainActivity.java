package es.udc.psi.p24lopez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArticlesAdapter mAdapter;
    protected static String KEY = "articulo";

    Button borrar, añadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.lista_elem);
        ArrayList<Article> initialData = new ArrayList<>();
        for (int i=0; i< 10; i++)
            initialData.add(new Article("Titulo " + i, "Subtitulo " + i, "Descripcion " + i));
        initRecycler(initialData);


        borrar = findViewById(R.id.borrar);
        añadir = findViewById(R.id.añadir);

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.removeItem();
            }
        });

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dialogo
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(R.string.Titulo_dialogo);
                builder.setMessage(R.string.Texto_dialogo);
                final EditText input = new EditText(v.getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String text = input.getText().toString();
                        try{
                            mAdapter.addItem(Integer.parseInt(text));
                        }catch (NumberFormatException e){

                        }

                    }
                });
                AlertDialog alerta = builder.create();
                alerta.show();

            }
        });

    }

    private void initRecycler(ArrayList<Article> articles) {
        mAdapter = new ArticlesAdapter(articles);
        //definimos listener de los comtenedores por si se clica en alguno
        mAdapter.setClickListener(new ArticlesAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("_TAG", " Item " + position );
                Toast.makeText(getApplicationContext(), "item " + position,
                        Toast.LENGTH_SHORT).show();
                lanzarIntent(view, position);

            }
        });
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    void lanzarIntent(View view, int position){
        Article clicked = mAdapter.getItem(position);
        Intent userProfileIntent = new Intent(this, ArticleActivity.class);
        userProfileIntent.putExtra(KEY,clicked);
        startActivity(userProfileIntent);
    }


}