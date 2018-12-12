package com.colabora.soluciones.convocatoriafeyac;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TarjetasActivity extends AppCompatActivity {

    private ImageView tarjeta1Back;
    private ImageView tarjeta1Front;
    private TextView txtTitutulo1;
    private TextView txtMensaje1;

    private ImageView tarjeta2Back;
    private ImageView tarjeta2Front;
    private TextView txtTitutulo2;
    private TextView txtMensaje2;

    private ImageView tarjeta3Back;
    private ImageView tarjeta3Front;
    private TextView txtTitutulo3;
    private TextView txtMensaje3;

    private ImageView tarjeta4Back;
    private ImageView tarjeta4Front;
    private TextView txtTitutulo4;
    private TextView txtMensaje4;

    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private RelativeLayout relativeLayout3;
    private RelativeLayout relativeLayout4;

    private SharedPreferences sharedPreferences;
    private boolean tarjeta1 = false;
    private boolean tarjeta2 = false;
    private boolean tarjeta3 = false;
    private boolean tarjeta4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetas);

        tarjeta1Back = (ImageView)findViewById(R.id.imgTarjeta1Back);
        tarjeta1Front = (ImageView)findViewById(R.id.imgTarjeta1Front);
        txtMensaje1 = (TextView)findViewById(R.id.txtTarjetasNoCreada1);
        txtTitutulo1 = (TextView)findViewById(R.id.txtTarjetasTitutlo1);

        tarjeta2Back = (ImageView)findViewById(R.id.imgTarjeta2Back);
        tarjeta2Front = (ImageView)findViewById(R.id.imgTarjeta2Front);
        txtMensaje2 = (TextView)findViewById(R.id.txtTarjetasNoCreada2);
        txtTitutulo2 = (TextView)findViewById(R.id.txtTarjetasTitutlo2);

        tarjeta3Back = (ImageView)findViewById(R.id.imgTarjeta3Back);
        tarjeta3Front = (ImageView)findViewById(R.id.imgTarjeta3Front);
        txtMensaje3 = (TextView)findViewById(R.id.txtTarjetasNoCreada3);
        txtTitutulo3 = (TextView)findViewById(R.id.txtTarjetasTitutlo3);

        tarjeta4Back = (ImageView)findViewById(R.id.imgTarjeta4Back);
        tarjeta4Front = (ImageView)findViewById(R.id.imgTarjeta4Front);
        txtMensaje4 = (TextView)findViewById(R.id.txtTarjetasNoCreada4);
        txtTitutulo4 = (TextView)findViewById(R.id.txtTarjetasTitutlo4);

        relativeLayout1 = (RelativeLayout)findViewById(R.id.relativeTarjetas1);
        relativeLayout2 = (RelativeLayout)findViewById(R.id.relativeTarjetas2);
        relativeLayout3 = (RelativeLayout)findViewById(R.id.relativeTarjetas3);
        relativeLayout4 = (RelativeLayout)findViewById(R.id.relativeTarjetas4);

        // Leemos la memoria para ver que tarjetas se han creado
        sharedPreferences = getSharedPreferences("misDatos", 0);
        tarjeta1 = sharedPreferences.getBoolean("tarjeta1",false);
        tarjeta2 = sharedPreferences.getBoolean("tarjeta2",false);
        tarjeta3 = sharedPreferences.getBoolean("tarjeta3",false);
        tarjeta4 = sharedPreferences.getBoolean("tarjeta4",false);

        if(tarjeta1){
            txtMensaje1.setVisibility(View.INVISIBLE);
        }
        else{
            txtTitutulo1.setVisibility(View.INVISIBLE);
            tarjeta1Front.setVisibility(View.INVISIBLE);
            tarjeta1Back.setVisibility(View.INVISIBLE);
        }

        if(tarjeta2){
            txtMensaje2.setVisibility(View.INVISIBLE);
        }
        else{
            txtTitutulo2.setVisibility(View.INVISIBLE);
            tarjeta2Front.setVisibility(View.INVISIBLE);
            tarjeta2Back.setVisibility(View.INVISIBLE);
        }

        if(tarjeta3){
            txtMensaje3.setVisibility(View.INVISIBLE);
        }
        else{
            txtTitutulo3.setVisibility(View.INVISIBLE);
            tarjeta3Front.setVisibility(View.INVISIBLE);
            tarjeta3Back.setVisibility(View.INVISIBLE);
        }

        if(tarjeta4){
            txtMensaje4.setVisibility(View.INVISIBLE);
        }
        else{
            txtTitutulo4.setVisibility(View.INVISIBLE);
            tarjeta4Front.setVisibility(View.INVISIBLE);
            tarjeta4Back.setVisibility(View.INVISIBLE);
        }

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tarjeta1){

                }
                else{
                    Intent i = new Intent(TarjetasActivity.this, CrearEditarTarjetasActivity.class);
                    startActivity(i);
                }
            }
        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tarjeta2){

                }
                else{
                    Intent i = new Intent(TarjetasActivity.this, CrearEditarTarjetasActivity.class);
                    startActivity(i);
                }
            }
        });

        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tarjeta3){

                }
                else{
                    Intent i = new Intent(TarjetasActivity.this, CrearEditarTarjetasActivity.class);
                    startActivity(i);
                }
            }
        });

        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tarjeta4){

                }
                else{
                    Intent i = new Intent(TarjetasActivity.this, CrearEditarTarjetasActivity.class);
                    startActivity(i);
                }
            }
        });


    }
}
