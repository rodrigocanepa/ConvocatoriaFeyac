package com.colabora.soluciones.convocatoriafeyac;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ImageView imgTarjetas;
    private ImageView imgCotizaciones;
    private ImageView imgFinanciero;
    private ImageView imgClientes;
    private ImageView imgProveedores;
    private ImageView imgRH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pyme Assistant");

        imgClientes = (ImageView)findViewById(R.id.imgMainClientes);
        imgCotizaciones = (ImageView)findViewById(R.id.imgMainCotizaciones);
        imgFinanciero = (ImageView)findViewById(R.id.imgMainFinanciero);
        imgProveedores = (ImageView)findViewById(R.id.imgMainProveedor);
        imgRH = (ImageView)findViewById(R.id.imgMainRH);
        imgTarjetas = (ImageView)findViewById(R.id.imgMainTarjetas);

        imgTarjetas.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgCotizaciones.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgFinanciero.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgProveedores.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgRH.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgClientes.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);

        imgCotizaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CotizacionesActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Perfil) {
            return true;
        }
        else if(id == R.id.action_Cerrar){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Cerrar sesión");
            builder.setMessage("¿Estas seguro de querer cerrar sesión?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            FirebaseAuth.getInstance().signOut();
                            Intent i = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            // Create the AlertDialog object and return it
            builder.create();
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }

}
