package com.example.norto.prova2bim.consulta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.norto.prova2bim.OrdenhaActivity;
import com.example.norto.prova2bim.R;
import com.example.norto.prova2bim.adapter.OrdenhaAdapter;
import com.example.norto.prova2bim.models.Ordenha;

public class ConsultaOrdenhaActivity extends AppCompatActivity {

    private Button btNew, btEdit;
    private ListView lvOrdenha;

    private OrdenhaAdapter ordenhaAdapter;
    private Ordenha ordenha;
    private final int ORDENHA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_ordenha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        loadComponents();
        
    }

    private void loadComponents() {
        btNew = findViewById(R.id.btNew);
        btEdit = findViewById(R.id.btEdit);
        lvOrdenha = findViewById(R.id.lvOrdenha);

        loadEvents();
    }

    private void loadEvents() {
        refreshList();

        lvOrdenha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ordenha = (Ordenha) lvOrdenha.getItemAtPosition(position);
            }
        });

        lvOrdenha.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ordenha = (Ordenha) lvOrdenha.getItemAtPosition(position);
                ordenha.delete();
                refreshList();
                return false;
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ordenha != null) {
                    Intent intent = new Intent(ConsultaOrdenhaActivity.this, OrdenhaActivity.class);
                    intent.putExtra("EDICAO", 1);
                    intent.putExtra("ORDENHA", ordenha.getId());
                    startActivityForResult(intent, ORDENHA);
                }
            }
        });

        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaOrdenhaActivity.this, OrdenhaActivity.class);
                startActivityForResult(intent, ORDENHA);
            }
        });
    }

    private void refreshList() {
            ordenhaAdapter = new OrdenhaAdapter(ConsultaOrdenhaActivity.this, Ordenha.listAll(Ordenha.class));
            lvOrdenha.setAdapter(ordenhaAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ORDENHA) {
            refreshList();
        }
    }

}
