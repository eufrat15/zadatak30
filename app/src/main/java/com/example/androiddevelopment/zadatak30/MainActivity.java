package com.example.androiddevelopment.zadatak30;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddevelopment.zadatak30.db.DatabaseHelper;
import com.example.androiddevelopment.zadatak30.model.Glumac;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                try {
                    addItem();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.action_preference:

                break;
            case R.id.action_about:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    private void refresh() {
        ListView listview = (ListView) findViewById(R.id.listaGlumaca);

        if (listview != null){
            ArrayAdapter<Glumac> adapter = (ArrayAdapter<Glumac>) listview.getAdapter();

            if(adapter!= null)
            {
                try {
                    adapter.clear();
                    List<Glumac> list = getDatabaseHelper().getGlumacDao().queryForAll();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addItem() throws SQLException {

        ListView listview = (ListView)findViewById(R.id.listaGlumaca);
        List<Glumac> list = getDatabaseHelper().getGlumacDao().queryForAll();
        ArrayAdapter<Glumac> dataAdapter = new ArrayAdapter<>(this, R.layout.list_item, list);
        listview.setAdapter(dataAdapter);

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.add_glumac);
        final EditText ime = (EditText) dialog.findViewById(R.id.ime);
        final EditText biografija = (EditText) dialog.findViewById(R.id.bio);
        final EditText ocena = (EditText) dialog.findViewById(R.id.ocena);
        final EditText godina = (EditText) dialog.findViewById(R.id.godina);

        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String glumacIme = ime.getText().toString();
                    String glumacBiografija = biografija.getText().toString();
                    double glumacOcena = Double.parseDouble(ocena.getText().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                    Date datum = null;
                    try {
                        datum = sdf.parse(datum.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Glumac glumac = new Glumac();
                    glumac.setIme(glumacIme);
                    glumac.setBiografija(glumacBiografija);
                    glumac.setOcena(glumacOcena);
                    glumac.setDatum(datum);


                    getDatabaseHelper().getGlumacDao().create(glumac);
                    refresh();
                    Toast.makeText(MainActivity.this, "Glumac ubacen", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();


                } catch (SQLException e) {
                    e.printStackTrace();

                }catch (NumberFormatException ee){
                    Toast.makeText(MainActivity.this, "Ocena mora biti broj", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
