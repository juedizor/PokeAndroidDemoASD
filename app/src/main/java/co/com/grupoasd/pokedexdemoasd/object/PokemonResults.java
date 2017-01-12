package co.com.grupoasd.pokedexdemoasd.object;

import java.util.List;

/**
 * Created by ASD on 11/01/2017.
 */

public class PokemonResults {
    List<Pokemon> pokemons;
    private int total;
    private String urlNext;
    private String urlPrevious;

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUrlNext() {
        return urlNext;
    }

    public void setUrlNext(String urlNext) {
        this.urlNext = urlNext;
    }

    public String getUrlPrevious() {
        return urlPrevious;
    }

    public void setUrlPrevious(String urlPrevious) {
        this.urlPrevious = urlPrevious;
    }
}
