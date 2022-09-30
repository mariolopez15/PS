package es.udc.psi.p23lopez;

import android.content.Context;
import android.content.Intent;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    Button botonA;
    Button botonB;
    Button botonC;
    EditText texto;
    OnButtonSelectedListener buttonListener;

    private View.OnClickListener AB_Listener = new View.OnClickListener() {
        public void onClick(View v) {
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

        }
    };

    public TopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopFragment.
     */
    // TODO: Rename and change types and number of parameters
    //QUITAR LOS PARAMETROS
    public static TopFragment newInstance(String param1, String param2) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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