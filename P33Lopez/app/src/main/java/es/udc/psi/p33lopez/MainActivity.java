package es.udc.psi.p33lopez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import es.udc.psi.p33lopez.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    String KEY = "Key_nombre";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        setUI();
        checkUser(); //comprueba el usuario actual y lo pone en el textView
    }

    @Override
    protected void onResume() {
        checkUser();
        super.onResume();
    }

    private void setUI(){

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        binding.userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //al pulsar el boton guardamos el usuario inidcado en el editText
                //cogemos el usuario del editText
                String user = binding.etUser.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //modificamos el valor de la calve usuario
                editor.putString(KEY, user);
                editor.apply();

                toFileHistorical();
            }
        });
    }

    private void toFileHistorical(){
        //File file = new File(this.getFilesDir(), "my_docs" );
        File newFile = new File(this.getFilesDir() , "historical.txt" );

        try (FileOutputStream fos = this.openFileOutput( "historical.txt" , MODE_PRIVATE | MODE_APPEND )) {
            //fos.write(fileContents .getBytes( StandardCharsets .UTF_8));
            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            fos.write("\t\n".getBytes( StandardCharsets.UTF_8));
            fos.write(binding.etUser.getText().toString().getBytes( StandardCharsets.UTF_8));
            fos.write("\t".getBytes( StandardCharsets.UTF_8));
            fos.write(formatter.format(date).getBytes( StandardCharsets.UTF_8));

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void checkUser(){
        //pone el hello_str por defecto en caso de no existir la clave KEY
        String user = sharedPreferences.getString(KEY, getString(R.string.hello_str));
        binding.tvHello.setText(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_settings:
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_share:
                //File docsPath = new File(this.getFilesDir(), "my_docs" );
                //File newFile = new File(docsPath , "default_text.txt" );
                File newFile = new File(this.getFilesDir() , "historical.txt" );
                Uri contentUri = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".fileprovider" ,
                        newFile);
                Intent shareIntent = new Intent(Intent.ACTION_SEND)
                        .putExtra( Intent.EXTRA_STREAM, contentUri )
                        .setType( "text/*" )
                        .setFlags( Intent.FLAG_GRANT_WRITE_URI_PERMISSION); // can be also for WRITE
                startActivity( Intent.createChooser(shareIntent , "Choose bar" ));
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}