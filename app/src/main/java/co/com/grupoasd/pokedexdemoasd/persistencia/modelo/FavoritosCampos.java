package co.com.grupoasd.pokedexdemoasd.persistencia.modelo;

/**
 * Created by Jose on 17/01/2017.
 */

public class FavoritosCampos {

    private final String database_table = "favoritos";
    private final String nombre = "nombre";
    private final String urlImage = "urlImage";
    private final String urlPokemon= "urlPokemon";

    public String getDatabaseTable() {
        return database_table;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getUrlPokemon() {
        return urlPokemon;
    }
}
