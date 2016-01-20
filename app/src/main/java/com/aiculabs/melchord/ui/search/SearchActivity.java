package com.aiculabs.melchord.ui.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aiculabs.melchord.R;
import com.aiculabs.melchord.data.model.ArtistSearch;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchMvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void showResults(List<ArtistSearch> artistSearches) {
        //TODO - Esto seguramente vaya en la vista de Resultados... :/
    }

    @Override
    public void showNoResults() {
        // TODO - Si no hay resultados, mostrar un aviso en la misma pantalla
    }

    @Override
    public void showError() {
        // TODO - Si hay error de conexión o similar, mostrar aviso ;)
    }
}
