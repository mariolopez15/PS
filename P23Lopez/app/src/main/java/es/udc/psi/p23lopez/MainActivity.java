package es.udc.psi.p23lopez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TopFragment

        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager_top = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction_top = fragmentManager_top.beginTransaction();
        TopFragment top = new TopFragment();
        fragmentTransaction_top.replace(R.id.top_fragment, top, "TOP_F");
        fragmentTransaction_top.commit();



        //DownFragment
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager_down = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction_down = fragmentManager_down.beginTransaction();
        DownFragment down = new DownFragment();
        fragmentTransaction_down.replace(R.id.down_fragment, down, "DOWN_F");
        fragmentTransaction_down.commit();
    }

    public void onButtonSelected(Character letra){

    }
}