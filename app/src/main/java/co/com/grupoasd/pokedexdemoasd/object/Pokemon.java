package co.com.grupoasd.pokedexdemoasd.object;

/**
 * Created by ASD on 02/01/2017.
 */

public class Pokemon {
    private String url;
    private String nombre;
    private PokemonDetalle pokemonDetalle;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PokemonDetalle getPokemonDetalle() {
        return pokemonDetalle;
    }

    public void setPokemonDetalle(PokemonDetalle pokemonDetalle) {
        this.pokemonDetalle = pokemonDetalle;
    }
}
