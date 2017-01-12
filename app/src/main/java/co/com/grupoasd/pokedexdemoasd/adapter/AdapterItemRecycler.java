package co.com.grupoasd.pokedexdemoasd.adapter;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.com.grupoasd.pokedexdemoasd.R;
import co.com.grupoasd.pokedexdemoasd.object.Item;

/**
 * Created by ASD on 28/12/2016.
 */

public class AdapterItemRecycler extends RecyclerView.Adapter<AdapterItemRecycler.ItemViewHolder> {

    private List<Item> datos;

    public AdapterItemRecycler(List<Item> datos){
        this.datos = datos;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ItemViewHolder tvh = new ItemViewHolder(itemView);
        return tvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = datos.get(position);
        holder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ItemViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtTitulo;
        private TextView txtSubtitulo;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtTitulo = (TextView) itemView.findViewById(R.id.LblTitulo);
            txtSubtitulo = (TextView) itemView.findViewById(R.id.LblSubTitulo);
        }

        public void bindItem(Item t) {
            txtTitulo.setText(t.getTitulo());
            txtSubtitulo.setText(t.getSubTitulo());
        }
    }
}
