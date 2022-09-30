package es.udc.psi.p23lopez;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopFragment extends Fragment {

    Button botonA;
    Button botonB;
    Button botonC;
    EditText texto;
    OnButtonSelectedListener buttonListener;

    private View.OnClickListener AB_Listener = new View.OnClickListener() {
        public void onClick(View v) {
            //listener de los botones que ejecutara la funcion para que la actvidad gestiones el evento
            switch(v.getId()) {
                case R.id.botonA: //si se ha pulsado A
                        buttonListener.onButton_AB_Selected('A');
                    break;

                case R.id.botonB:
                    buttonListener.onButton_AB_Selected('B');
                    break;
            }
        }
    };
    private View.OnClickListener C_Listener = new View.OnClickListener() {
        public void onClick(View v) {
            buttonListener.onButton_C_Selected(texto.getText().toString());
        }
    };

    public TopFragment() {
        // Required empty public constructor
    }


    public static TopFragment newInstance() {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        elementos();
        //definimos los listeners de los botones
        botonA.setOnClickListener(AB_Listener);
        botonB.setOnClickListener(AB_Listener);
        botonC.setOnClickListener(C_Listener);
    }

    private void elementos(){
        //enlazamos los elementos con su atributo
        botonA = getView().findViewById(R.id.botonA);
        botonB = getView().findViewById(R.id.botonB);
        botonC = getView().findViewById(R.id.botonC);
        texto = getView().findViewById(R.id.texto);
    }



    public interface OnButtonSelectedListener {
        public void onButton_AB_Selected(Character letra);
        public void onButton_C_Selected(String url);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            buttonListener = (OnButtonSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " implement OnArticleSelectedListener");
        }
    }



}