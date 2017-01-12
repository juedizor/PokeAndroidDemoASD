package co.com.grupoasd.pokedexdemoasd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs;
    EditText textUser;
    EditText textPass;
    Button buttonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String user = prefs.getString("user", "vacio");
        if (!user.equals("vacio")) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
        textUser = (EditText) findViewById(R.id.user);
        textPass = (EditText) findViewById(R.id.password);
        buttonBuscar = (Button) findViewById(R.id.button_entrar);
        buttonBuscar.setOnClickListener(this);
    }

    private boolean validarCampos(){
        String user = textUser.getText().toString();
        String password = textPass.getText().toString();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(user)) {
            Toast.makeText(this,"Por favor verifique todos los campos", Toast.LENGTH_LONG).show();
            return false;
        }

        if (!isPasswordValid(password)) {
            Toast.makeText(this,"Esta contraseña es demasiado corta", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onClick(View view) {
        if(validarCampos()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user", textUser.getText().toString());
            editor.commit();
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("user", textUser.getText().toString());
            startActivity(intent);
        }
    }
}
