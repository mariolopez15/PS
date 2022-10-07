package es.udc.psi.p24lopez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>{

    private final ArrayList<Article> mDataset;
    private static OnItemClickListener clickListener;

    public ArticlesAdapter(ArrayList<Article> myDataset) {
        this.mDataset = myDataset;
    }


    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }


    //Representa cada elemento de la lista que se mostrará
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //repsenenta a los elemtos definidos en el xml que define la estructura del contenedor
        //En este caso cada contendor estara formado por dos textView uno para el titulo y otro para el subtitulo
        public TextView title;
        public TextView subtitle;
        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);
            view.setOnClickListener(this);
        }
        public void bind(Article article) {
            title.setText(article.getTitle());
            subtitle.setText(article.getSubtitle());
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_title, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    public Article getItem(int pos){
        return mDataset.get(pos);
    }

    public void addItem(int count){
        int ultimo=getItemCount();
        for(int i=ultimo; i<ultimo+count; i++){
            mDataset.add(new Article("Titulo " + i, "Subtitulo " + i, "Descripcion " + i));
        }
    }

    public void removeItem(){
        int ultimo=getItemCount();
        for(int i=ultimo; i>0; i--){
            mDataset.remove(0);
            notifyItemRemoved(0);

        }
    }



}
