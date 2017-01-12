package co.com.grupoasd.pokedexdemoasd.service.iface;

import java.util.List;

import co.com.grupoasd.pokedexdemoasd.object.Pokemon;
import co.com.grupoasd.pokedexdemoasd.object.PokemonDetalle;
import co.com.grupoasd.pokedexdemoasd.object.PokemonResults;

/**
 * Created by ASD on 22/12/2016.
 */

public interface PokeApiIface {
    public PokemonResults getPokemonsData(String url);
    public void setPokemonDetalle(String url, PokemonDetalle pokemon);
    public void setPokemonUrlImage(String url, Pokemon pokemon);
}
