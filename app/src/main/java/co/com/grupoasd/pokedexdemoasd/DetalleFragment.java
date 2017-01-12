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

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (pokemon.getPokemonDetalle() != null) {
            textViewNombre.setText(pokemon.getNombre());
            textViewTipo.setText(getTipos(pokemon.getPokemonDetalle().getType()));
            textViewAlto.setText(pokemon.getPokemonDetalle().getAlto() + "");
            textViewAncho.setText(pokemon.getPokemonDetalle().getAncho() + "");
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
