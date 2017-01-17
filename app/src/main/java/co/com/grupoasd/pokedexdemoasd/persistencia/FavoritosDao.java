package co.com.grupoasd.pokedexdemoasd.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import co.com.grupoasd.pokedexdemoasd.persistencia.modelo.Favoritos;

/**
 * Created by ASD on 17/01/2017.
 */

public class FavoritosDao {

    private PokemonSQLiteHelper sqLiteHelper;
    private SQLiteDatabase database;
    private Context context;
    private Favoritos favoritos;

    FavoritosDao(Context context) {
        this.context = context;
        favoritos = new Favoritos();
    }

    FavoritosDao open() throws SQLException {
        sqLiteHelper = new PokemonSQLiteHelper(context, null, 1);
        database = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        sqLiteHelper.close();
    }

    public long createFavorito(String nombre, String urlImage, String urlPokemon) {
        ContentValues values = createContentValues(nombre, urlImage, urlPokemon);
        return database.insert(favoritos.getDatabaseTable(), null, values);
    }

    public Cursor selectAll() {
        return database.query(favoritos.getDatabaseTable(), new String[]{favoritos.getNombre(),
                        favoritos.getUrlImage(), favoritos.getUrlPokemon()}, null, null, null,
                null, null);
    }

    private ContentValues createContentValues(String nombre, String urlImagen, String urlPokemon) {
        ContentValues values = new ContentValues();
        values.put(favoritos.getNombre(), nombre);
        values.put(favoritos.getUrlImage(), urlImagen);
        values.put(favoritos.getUrlPokemon(), urlPokemon);
        return values;
    }
}
