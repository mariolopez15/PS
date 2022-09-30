package es.udc.psi.p23lopez;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


public class DownFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Character mParam2;

    TextView textView;
    WebView navegador;

    public DownFragment() {
        // Required empty public constructor
    }


    /*
    public static DownFragment newInstance(String param1) {
        DownFragment fragment = new DownFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putChar(ARG_PARAM2, null);
        fragment.setArguments(args);
        return fragment;
    }

    public static DownFragment newInstance(Character param2) {
        DownFragment fragment = new DownFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, null);
        args.putChar(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //Param2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_down, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        elementos();
        //definimos los listeners de los botones

    }

    private void elementos(){
        //enlazamos los elementos con su atributo
        textView = getView().findViewById(R.id.textView);
        navegador = getView().findViewById(R.id.navegador);
    }

    protected void actulizarTexto(Character letra){
        switch (letra){
            case 'A':
                textView.setText(R.string.boton_pulsadoA);
                break;
            case 'B':
                textView.setText(R.string.boton_pulsadoB);
                break;
        }

    }
    protected void actualizarTexto(String url){
        textView.setText(url);
    }
    protected void actualizarNavegador(String url){
        navegador.loadUrl(url);
    }
}