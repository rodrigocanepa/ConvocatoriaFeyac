package com.colabora.soluciones.convocatoriafeyac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiagnosticoActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView txtActividades;
    private SharedPreferences sharedPreferences;
    private Button btnDiagnostico;

    private boolean diagnostico = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico);

        txtActividades = (TextView)findViewById(R.id.txtDiagnosticoActividades);
        linearLayout = (LinearLayout)findViewById(R.id.linearDiagnostico);
        btnDiagnostico = (Button)findViewById(R.id.btnRealizarDiagnostico);

        // Leemos la memoria para ver que tarjetas se han creado
        sharedPreferences = getSharedPreferences("misDatos", 0);
        diagnostico = sharedPreferences.getBoolean("diagnostico",false);

        if(diagnostico){
            btnDiagnostico.setVisibility(View.INVISIBLE);
        }
        else{
            linearLayout.setVisibility(View.INVISIBLE);
        }

        btnDiagnostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiagnosticoActivity.this, RealizarDiagnosticoActivity.class);
                startActivity(i);
            }
        });



    }
}
