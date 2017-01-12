package co.com.grupoasd.pokedexdemoasd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.com.grupoasd.pokedexdemoasd.object.Pokemon;
import co.com.grupoasd.pokedexdemoasd.object.PokemonResults;
import co.com.grupoasd.pokedexdemoasd.service.iface.PokeApiIface;
import co.com.grupoasd.pokedexdemoasd.service.impl.PokeApiImpl;

import static co.com.grupoasd.pokedexdemoasd.service.impl.PokeApiImpl.PREFIJO_POKEMON;
import static co.com.grupoasd.pokedexdemoasd.service.modelo.BaseService.PREFIJO_API;

public class FragmentsActivity extends AppCompatActivity {

    RadioButton radioButtonPorID;
    RadioButton radioButtonTodos;
    RadioGroup radioGroup;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    EditText textBuscar;
    ImageButton buttonBuscar;
    Button buttonNext;
    ProgressDialog progressDialog;
    PokemonResults pokemonResults = new PokemonResults();
    private PokeApiIface pokeApi;
    List<Pokemon> pokemons;
    ListadoFragment listadoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tabs_fragment);
        radioGroup = (RadioGroup) findViewById(R.id.radio_criterio);
        radioButtonPorID = (RadioButton) findViewById(R.id.radioButtonPorID);
        radioButtonTodos = (RadioButton) findViewById(R.id.radioButtonTodos);
        radioButtonPorID.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonPorID.isChecked()) {
                    textBuscar.setEnabled(true);
                    buttonNext.setEnabled(false);
                } else {
                    textBuscar.setEnabled(false);
                    buttonNext.setEnabled(true);
                }
            }
        });
        textBuscar = (EditText) findViewById(R.id.editTextID);
        buttonBuscar = (ImageButton) findViewById(R.id.imageButtonBuscar);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                if(pokemonResults.getUrlNext()!=null){
                    actionBuscar(pokemonResults.getUrlNext());
                }else{
                    actionBuscar("");
                }
            }
        });
        buttonNext.setEnabled(false);
        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBuscar("");
            }
        });
        pokeApi = new PokeApiImpl();
    }

    public void actionBuscar(String url) {
        if(radioButtonPorID.isChecked()){
            if (!textBuscar.getText().toString().equals("")) {
                int id = Integer.parseInt(textBuscar.getText().toString());
                if (id > 0) {

                } else {
                    Toast.makeText(this, "Por favor ingrese un valor mayor a 0", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Por favor ingrese un valor mayor a 0", Toast.LENGTH_LONG).show();
            }
        }else{
            BuscarAsyncTask buscarAsyncTask = new BuscarAsyncTask();
            buscarAsyncTask.execute(url);
        }
    }

    private class BuscarAsyncTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            inicializarProgressDialog();
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            String urlApi;
            if (urls.length > 0 && !TextUtils.isEmpty(urls[0])) {
                urlApi = urls[0];
                pokemonResults = pokeApi.getPokemonsData(urlApi);
            } else {
                pokemonResults = pokeApi.getPokemonsData(PREFIJO_API + PREFIJO_POKEMON);
            }
            pokemons = pokemonResults.getPokemons();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            dismissProgressDialog();
            if (result) {
                if (!pokemons.isEmpty()) {
                    fragmentManager = getSupportFragmentManager();
                    if(listadoFragment != null){
                        transaction = fragmentManager.beginTransaction();
                        transaction.remove(listadoFragment).commit();
                    }
                    transaction = fragmentManager.beginTransaction();
                    listadoFragment = new ListadoFragment();
                    listadoFragment.setPokemons(pokemons);
                    transaction.add(R.id.contenedor_fragment, listadoFragment);
                    transaction.commitAllowingStateLoss();
                }
            }
        }
    }

    private void inicializarProgressDialog() {
        TextView title = new TextView(this);
        title.setText("Buscando pokemon");
        //title.setBackgroundColor(Color.argb(255, 3, 169, 244));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCustomTitle(title);
        progressDialog.setMessage("Por favor espere");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
