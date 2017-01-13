package co.com.grupoasd.pokedexdemoasd.service.impl;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.com.grupoasd.pokedexdemoasd.object.Pokemon;
import co.com.grupoasd.pokedexdemoasd.object.PokemonDetalle;
import co.com.grupoasd.pokedexdemoasd.object.PokemonEspecie;
import co.com.grupoasd.pokedexdemoasd.object.PokemonResults;
import co.com.grupoasd.pokedexdemoasd.service.ApiCall;
import co.com.grupoasd.pokedexdemoasd.service.iface.PokeApiIface;
import co.com.grupoasd.pokedexdemoasd.service.modelo.BaseService;
import okhttp3.OkHttpClient;

/**
 * Created by ASD on 22/12/2016.
 */

public class PokeApiImpl extends BaseService implements PokeApiIface {

    public static final String PREFIJO_POKEMON = "pokemon/";

    @Override
    public PokemonResults getPokemonsData(String url, int id) {
        PokemonResults pokemonResults = new PokemonResults();
        List<Pokemon> pokemons = new ArrayList<>();
        String count = "";
        String urlPrevious = "";
        String urlNext = "";
        try {
            JSONObject jsonObject = getJsonObjectPokemon(url + (id != 0 ? id : ""));
            if (id > 0) {
                setPokemon(pokemons, jsonObject, url + id);
                setPokemonDetalle(jsonObject, pokemons.get(0).getPokemonDetalle());
            } else {
                count = jsonObject.getString("count");
                urlPrevious = jsonObject.getString("previous");
                JSONArray resultado = jsonObject.getJSONArray("results");
                urlNext = jsonObject.getString("next");
                setListaPokemon(pokemons, resultado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pokemonResults.setPokemons(pokemons);
        pokemonResults.setTotal(!count.equals("") ? Integer.parseInt(count) : 0);
        pokemonResults.setUrlNext(!urlNext.equals("") ? urlNext : "");
        pokemonResults.setUrlPrevious(!urlPrevious.equals("") ? urlPrevious : "");

        return pokemonResults;
    }

    @Override
    public void setPokemonDetalle(String url, PokemonDetalle pokemonDetalle) {
        try {
            JSONObject jsonObject = getJsonObjectPokemon(url);
            setPokemonDetalle(jsonObject, pokemonDetalle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setPokemonDetalle(JSONObject jsonObject, PokemonDetalle pokemonDetalle) throws JSONException, IOException {
        pokemonDetalle.setAlto(jsonObject.getInt("height"));
        pokemonDetalle.setAncho(jsonObject.getInt("weight"));
        PokemonEspecie pokemonEspecie = new PokemonEspecie();
        JSONObject jsonObjectEspecie = jsonObject.getJSONObject("species");
        pokemonEspecie.setUrlEspecie(jsonObjectEspecie.getString("url"));
        JSONObject jsonObjectEspecieDetalle = getJsonObjectPokemon(pokemonEspecie.getUrlEspecie());
        pokemonEspecie.setColor(jsonObjectEspecieDetalle.getJSONObject("color").getString("name"));
        pokemonEspecie.setForma(jsonObjectEspecieDetalle.getJSONObject("shape").getString("name"));
        pokemonEspecie.setHabitad(jsonObjectEspecieDetalle.getJSONObject("habitat").getString("name"));
        pokemonDetalle.setPokemonEspecie(pokemonEspecie);
        String[] type = getTypes(jsonObject.getJSONArray("types"));
        pokemonDetalle.setType(type);
    }

    @Override
    public void setPokemonUrlImage(String url, Pokemon pokemon) {
        PokemonDetalle pokemonDetalle = new PokemonDetalle();
        try {
            JSONObject jsonObject = getJsonObjectPokemon(url);
            JSONObject jsonObjectSprites = jsonObject.getJSONObject("sprites");
            pokemonDetalle.setFrontDefaultImage(jsonObjectSprites.getString("front_default"));
            pokemon.setPokemonDetalle(pokemonDetalle);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObjectPokemon(String url) throws IOException {
        JSONObject respJSON = null;
        try {
            OkHttpClient client = new OkHttpClient();
            String result = ApiCall.GET(client, url);
            respJSON = new JSONObject(result);
        } catch (IOException ex) {
            Log.e(PokeApiImpl.class.getName(), "Error getJsonObject!", ex);
        } catch (Exception ex) {
            Log.e(PokeApiImpl.class.getName(), "Error getJsonObject!", ex);
        }
        return respJSON;
    }


    private void setListaPokemon(List<Pokemon> pokemons, JSONArray resultado) throws JSONException {
        for (int i = 0; i < resultado.length(); i++) {
            Pokemon pokemon = new Pokemon();
            JSONObject jsonObject = resultado.getJSONObject(i);
            pokemon.setNombre(jsonObject.getString("name"));
            pokemon.setUrl(jsonObject.getString("url"));
            setPokemonUrlImage(pokemon.getUrl(), pokemon);
            pokemons.add(pokemon);
        }
    }

    private void setPokemon(List<Pokemon> pokemons, JSONObject jsonObject, String url) throws JSONException, IOException {
        Pokemon pokemon = new Pokemon();
        PokemonDetalle pokemonDetalle = new PokemonDetalle();
        pokemon.setUrl(url);
        pokemon.setNombre(jsonObject.getString("name"));
        JSONObject jsonObjectSprites = jsonObject.getJSONObject("sprites");
        pokemonDetalle.setFrontDefaultImage(jsonObjectSprites.getString("front_default"));
        pokemon.setPokemonDetalle(pokemonDetalle);
        setPokemonDetalle(jsonObject, pokemonDetalle);
        pokemons.add(pokemon);
    }

    private String[] getTypes(JSONArray jsonArray) throws JSONException {
        String[] tipos = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            tipos[i] = jsonArray.getJSONObject(i).getJSONObject("type").getString("name");
        }
        return tipos;
    }

}
