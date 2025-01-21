package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.appmunicipal.R;

import java.util.List;

import Objects.denuncias;

public class AdapterComplaint extends RecyclerView.Adapter<AdapterComplaint.ViewHolder>{
    private List<denuncias> listaDenuncias;
    private LayoutInflater listaInflater;

    public AdapterComplaint (Context context, List<denuncias> lista){
        this.listaInflater = LayoutInflater.from(context);
        this.listaDenuncias= lista;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = listaInflater.inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        denuncias item = listaDenuncias.get(position);
        holder.textView.setText(item.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaDenuncias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
