package co.com.grupoasd.pokedexdemoasd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListarActivities extends AppCompatActivity {

    Button buttonListView;
    Button buttonViewHolder;
    Button buttonAsyncTask;
    Button buttonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_activities);
        buttonListView = (Button) findViewById(R.id.buttonActivityListView);
        buttonViewHolder = (Button) findViewById(R.id.buttonActivityViewHolder);
        buttonAsyncTask = (Button) findViewById(R.id.buttonAsyncTask);
        buttonFragment = (Button) findViewById(R.id.buttonFragment);

        buttonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarActivities.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarActivities.this, RecyclerViewActivity.class);
                startActivity(intent);

            }
        });
        buttonAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarActivities.this, TabsActivityRest.class);
                startActivity(intent);
            }
        });
        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarActivities.this, FragmentsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
