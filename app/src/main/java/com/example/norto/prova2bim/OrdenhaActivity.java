package com.example.norto.prova2bim;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.norto.prova2bim.consulta.ConsultaOrdenhaActivity;
import com.example.norto.prova2bim.models.MatrizLeitera;
import com.example.norto.prova2bim.models.Ordenha;
import com.orm.SugarContext;

import java.util.Calendar;
import java.util.Date;

public class OrdenhaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private EditText etId, etQnt;
    private TextView tvDtHora;
    private Spinner spMatriz;
    private Button btSave, btCancel;

    private Ordenha ordenha;

    private int day, month, year;
    private Calendar calendar = Calendar.getInstance();
    private ArrayAdapter<MatrizLeitera> matrizArrayAdapter = null;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SugarContext.init(this); //Start Sugar;

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(OrdenhaActivity.this, this, year, month, day);

        loadComponents();
    }

    private void loadComponents() {

        etId = findViewById(R.id.etId);
        etQnt = findViewById(R.id.etQnt);
        tvDtHora = findViewById(R.id.tvDtHora);
        spMatriz = findViewById(R.id.spMatriz);
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);

        loadEvents();
    }

    private void loadEvents() {

        refreshSpMatriz();

        tvDtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrdenha();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        try {
            if (getIntent().getExtras() != null) {
                if (getIntent().getExtras().getInt("EDICAO", 0) == 1) {
                    ordenha = Ordenha.findById(Ordenha.class, getIntent().getExtras().getLong("ORDENHA"));
                    btSave.setText(R.string.lbAtualizar);
                    setFields();
                }
            } else
                getLastId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setFields() {
        etId.setText(String.valueOf(ordenha.getIdentifcador()));
        etQnt.setText(String.valueOf(ordenha.getQntLitros()));
        tvDtHora.setText(String.valueOf(ordenha.getDtHora()));
        spMatriz.setSelection(matrizArrayAdapter.getPosition(ordenha.getMatriz()));
    }

    private void saveOrdenha() {
        if (ordenha != null) {
            ordenha.setIdentifcador(Integer.parseInt(etId.getText().toString().trim()));
            ordenha.setMatriz((MatrizLeitera) spMatriz.getSelectedItem());
            ordenha.setQntLitros(Integer.parseInt(etQnt.getText().toString().trim()));
            ordenha.setDtHora(new Date());
            ordenha.update();
        } else {
            ordenha = new Ordenha();
            ordenha.setIdentifcador(Integer.parseInt(etId.getText().toString().trim()));
            ordenha.setMatriz((MatrizLeitera) spMatriz.getSelectedItem());
            ordenha.setQntLitros(Integer.parseInt(etQnt.getText().toString().trim()));
            ordenha.setDtHora(new Date());
            ordenha.save();
            getLastId();
        }
        setResult(RESULT_OK);
        finish();
    }

    private void refreshSpMatriz() {
        ArrayAdapter<MatrizLeitera> matrizArrayAdapter = new ArrayAdapter<>(
                OrdenhaActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                MatrizLeitera.listAll(MatrizLeitera.class));
        spMatriz.setAdapter(matrizArrayAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lancamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_OrdenhaList) {
            Intent intent = new Intent(OrdenhaActivity.this, ConsultaOrdenhaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        tvDtHora.setText(day + "/" + month + 1 + "/" + year);
    }

    private void getLastId() {
        Ordenha lastOrdenha = Ordenha.last(Ordenha.class);

        int codigo = lastOrdenha != null ? lastOrdenha.getIdentifcador() + 1 : 1;
        etId.setText(String.valueOf(codigo));
    }
}
