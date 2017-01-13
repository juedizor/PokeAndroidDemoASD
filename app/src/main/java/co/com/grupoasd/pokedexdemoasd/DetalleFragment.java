package co.com.grupoasd.pokedexdemoasd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import co.com.grupoasd.pokedexdemoasd.object.Pokemon;


public class DetalleFragment extends Fragment {

    ImageView imageViewIcono;
    TextView textViewNombre;
    TextView textViewTipo;
    TextView textViewAncho;
    TextView textViewAlto;
    TextView textViewColor;
    TextView textViewForma;
    TextView textViewHabitad;
    Pokemon pokemon;

    public DetalleFragment() {
        // Required empty public constructor
        pokemon = new Pokemon();
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poke_detalle, container, false);
        imageViewIcono = (ImageView) view.findViewById(R.id.imageViewPoke);
        textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
        textViewTipo = (TextView) view.findViewById(R.id.textViewTipo);
        textViewAncho = (TextView) view.findViewById(R.id.textViewAncho);
        textViewAlto = (TextView) view.findViewById(R.id.textViewAlto);
        textViewColor = (TextView) view.findViewById(R.id.textViewColor);
        textViewForma = (TextView) view.findViewById(R.id.textViewForma);
        textViewHabitad = (TextView) view.findViewById(R.id.textViewHabitad);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (pokemon.getPokemonDetalle() != null) {
            textViewNombre.setText(pokemon.getNombre().toUpperCase());
            textViewTipo.setText(textViewTipo.getText()+""+getTipos(pokemon.getPokemonDetalle().getType()));
            textViewAlto.setText(textViewAlto.getText()+""+pokemon.getPokemonDetalle().getAlto() + "");
            textViewAncho.setText(textViewAncho.getText()+""+pokemon.getPokemonDetalle().getAncho() + "");
            textViewColor.setText(textViewColor.getText()+""+pokemon.getPokemonDetalle().getPokemonEspecie().getColor() + "");
            textViewForma.setText(textViewForma.getText()+""+pokemon.getPokemonDetalle().getPokemonEspecie().getForma() + "");
            textViewHabitad.setText(textViewHabitad.getText()+""+pokemon.getPokemonDetalle().getPokemonEspecie().getHabitad()+ "");
            Glide.with(getActivity())
                    .load(pokemon.getPokemonDetalle().getFrontDefaultImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .fitCenter()
                    .error(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageViewIcono);
        }
    }

    private String getTipos(String[] type) {
        String tipos = "";
        for (int i = 0; i < type.length; i++) {
            tipos += type[i] + ", ";
        }
        return tipos;
    }
}
