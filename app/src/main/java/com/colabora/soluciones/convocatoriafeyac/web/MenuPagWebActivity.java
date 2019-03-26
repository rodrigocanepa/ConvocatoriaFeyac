package com.colabora.soluciones.convocatoriafeyac.web;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.R;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class MenuPagWebActivity extends AppCompatActivity {

    private ImageView imgServicios;
    private ImageView imgProductos;
    private ImageView imgSalud;
    private ImageView imgAplicaciones;
    private ImageView imgModa;
    private ImageView imgComida;
    private FabSpeedDial speedDialView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pag_web);
        setTitle("Crear mi página web");

        imgServicios = (ImageView)findViewById(R.id.imgWebServicios);
        imgProductos = (ImageView)findViewById(R.id.imgWebProductos);
        imgSalud = (ImageView)findViewById(R.id.imgWebSalud);
        imgAplicaciones = (ImageView)findViewById(R.id.imgWebApps);
        imgModa = (ImageView)findViewById(R.id.imgWebModa);
        imgComida = (ImageView)findViewById(R.id.imgWebComida);
        speedDialView = (FabSpeedDial)findViewById(R.id.speedDial);

        imgServicios.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgProductos.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgSalud.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgAplicaciones.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgModa.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);
        imgComida.setColorFilter(Color.argb(150,20,20,20), PorterDuff.Mode.DARKEN);

        speedDialView.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                //TODO: Start some activity
                int id = menuItem.getItemId();

                if(id == R.id.action_ver_mi_pag){
                    Toast.makeText(getApplicationContext(), "Ver mi pag web", Toast.LENGTH_LONG).show();
                    return true;
                }
                else if(id == R.id.action_editar_mi_pag){
                    Toast.makeText(getApplicationContext(), "Editar mi pag web", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });

        imgComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "comida");
                startActivity(i);
            }
        });

        imgServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "servicios");
                startActivity(i);
            }
        });

        imgProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "productos");
                startActivity(i);
            }
        });

        imgSalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "salud");
                startActivity(i);
            }
        });

        imgAplicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "aplicaciones");
                startActivity(i);
            }
        });

        imgModa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuPagWebActivity.this, WebVisualizacionPreviaActivity.class);
                i.putExtra(WebVisualizacionPreviaActivity.PAG_WEB, "moda");
                startActivity(i);
            }
        });
    }
}
