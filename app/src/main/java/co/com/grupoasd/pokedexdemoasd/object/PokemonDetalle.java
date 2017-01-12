package co.com.grupoasd.pokedexdemoasd.object;

/**
 * Created by ASD on 02/01/2017.
 */

public class PokemonDetalle {
    private int ancho;
    private int alto;
    private String frontDefaultImage;
    private PokemonEspecie pokemonEspecie;
    private String[] type;

    public String getFrontDefaultImage() {
        return frontDefaultImage;
    }

    public void setFrontDefaultImage(String frontDefaultImage) {
        this.frontDefaultImage = frontDefaultImage;
    }

    public PokemonEspecie getPokemonEspecie() {
        return pokemonEspecie;
    }

    public void setPokemonEspecie(PokemonEspecie pokemonEspecie) {
        this.pokemonEspecie = pokemonEspecie;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
