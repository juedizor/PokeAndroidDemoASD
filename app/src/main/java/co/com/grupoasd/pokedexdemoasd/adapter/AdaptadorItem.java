package co.com.grupoasd.pokedexdemoasd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import co.com.grupoasd.pokedexdemoasd.R;
import co.com.grupoasd.pokedexdemoasd.object.Item;

/**
 * Created by ASD on 28/12/2016.
 */
// comentario
public class AdaptadorItem extends ArrayAdapter {

    Item[] datos;

    public AdaptadorItem(Context context, Item[] datos) {
        super(context, R.layout.list_item, datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_item, null);
        TextView titulo = (TextView) item.findViewById(R.id.LblTitulo);
        titulo.setText(datos[position].getTitulo());
        TextView subTitulo = (TextView) item.findViewById(R.id.LblSubTitulo);
        subTitulo.setText(datos[position].getSubTitulo());
        return item;
    }
}
