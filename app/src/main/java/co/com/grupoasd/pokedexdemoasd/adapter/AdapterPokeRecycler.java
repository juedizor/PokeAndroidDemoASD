package co.com.grupoasd.pokedexdemoasd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import co.com.grupoasd.pokedexdemoasd.R;
import co.com.grupoasd.pokedexdemoasd.object.Pokemon;

/**
 * Created by ASD on 28/12/2016.
 */

public class AdapterPokeRecycler extends RecyclerView.Adapter<AdapterPokeRecycler.ItemViewHolder> implements View.OnClickListener{

    private List<Pokemon> datos;
    View.OnClickListener listener;
    private Context context;

    public AdapterPokeRecycler(List<Pokemon> datos, Context context){
        this.datos = datos;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pokemon, parent, false);
        itemView.setOnClickListener(this);
        ItemViewHolder tvh = new ItemViewHolder(itemView);
        return tvh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Pokemon item = datos.get(position);
        holder.bindItem(item);
        holder.getView().setTag(item);
        Glide.with(context)
                .load(item.getPokemonDetalle().getFrontDefaultImage())
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.getImageViewPoke());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public static class ItemViewHolder
            extends RecyclerView.ViewHolder {

        private ImageView imageViewPoke;
        private TextView textViewNombre;
        View v;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageViewPoke = (ImageView) itemView.findViewById(R.id.imageViewPoke);
            textViewNombre = (TextView) itemView.findViewById(R.id.textViewNombrePoke);
            v = itemView;
        }

        public ImageView getImageViewPoke(){
            return imageViewPoke;
        }

        public View getView(){
            return v;
        }

        public void bindItem(Pokemon t) {
            textViewNombre.setText(t.getNombre());
        }
    }
}
