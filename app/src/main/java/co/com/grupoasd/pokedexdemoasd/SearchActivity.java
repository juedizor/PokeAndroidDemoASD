package co.com.grupoasd.pokedexdemoasd;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import co.com.grupoasd.pokedexdemoasd.adapter.AdaptadorItem;
import co.com.grupoasd.pokedexdemoasd.object.Item;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    EditText textBuscar;
    ImageButton buttonBuscar;
    ListView listView;
    String user;
    TextView textViewNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textBuscar = (EditText) findViewById(R.id.editTextID);
        buttonBuscar = (ImageButton) findViewById(R.id.imageButtonBuscar);
        buttonBuscar.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.list_view);
        textViewNombre = (TextView) findViewById(R.id.textView);
        user = getIntent().getExtras().getString("user");
        String textoNombre = user+" "+textViewNombre.getText();
        textViewNombre.setText(textoNombre);
    }

    @Override
    public void onClick(View view) {
        if(!textBuscar.getText().toString().equals("")){
            int tamanio = Integer.parseInt(textBuscar.getText().toString());
            Item[] datos = getItems(tamanio);
            AdaptadorItem adaptador = new AdaptadorItem(this,datos);
            listView.setAdapter(adaptador);
        }else{
            Toast.makeText(this,"Por favor ingrese un valor", Toast.LENGTH_LONG).show();
        }
    }

    private Item[] getItems(int tamanio){
        Item[] datos = new Item[tamanio];
        for (int i = 0; i<tamanio;i++){
            datos[i] = new Item("Título "+(i+1), "Subtítulo "+(i+1));
        }
        return datos;
    }
}
