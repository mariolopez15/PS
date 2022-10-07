package es.udc.psi.p24lopez;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


import es.udc.psi.p24lopez.databinding.ActivityArticleBinding;


public class ArticleActivity extends AppCompatActivity {

    private ActivityArticleBinding binding;
    private String KEY = "articulo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Article articulo = (Article) bundle.get (MainActivity.KEY);
            binding.Title.setText(articulo.getTitle());
            binding.subtitle.setText(articulo.getSubtitle());
            binding.description.setText(articulo.getDescription());
        }
        //Article articulo = getIntent().getParcelableExtra(KEY);

    }
}