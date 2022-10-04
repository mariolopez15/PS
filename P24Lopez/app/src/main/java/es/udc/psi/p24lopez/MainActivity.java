package es.udc.psi.p24lopez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArticlesAdapter mAdapter;

    Button clear, añadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.lista_elem);
        ArrayList<Article> initialData = new ArrayList<>();
        for (int i=0; i< 10; i++)
            initialData.add(new Article("Titulo " + i, "Subtitulo " + i, "Descripcion " + i));
        initRecycler(initialData);
    }

    private void initRecycler(ArrayList<Article> articles) {
        mAdapter = new ArticlesAdapter(articles);
        mAdapter.setClickListener(new ArticlesAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("_TAG", " Item " + position );
                Toast.makeText(getApplicationContext(), "item " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }


}