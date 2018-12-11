package com.colabora.soluciones.convocatoriafeyac;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CotizacionesActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizaciones);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.fabAddCotizacion);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CotizacionesActivity.this, NuevaCotizacionActivity.class);
                startActivity(i);
            }
        });

    }
}
