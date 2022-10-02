package es.udc.psi.p23lopez;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


public class DownFragment extends Fragment {



    TextView textView;
    WebView navegador;

    public DownFragment() {
        // Required empty public constructor
    }


    public static DownFragment newInstance() {
        DownFragment fragment = new DownFragment();
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