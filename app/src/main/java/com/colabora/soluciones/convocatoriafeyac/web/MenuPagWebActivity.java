package com.colabora.soluciones.convocatoriafeyac.web;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.R;
import com.colabora.soluciones.convocatoriafeyac.web.aplicaciones.WebAppsSeccion1Activity;
import com.colabora.soluciones.convocatoriafeyac.web.comida.WebsComidaSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.moda.WebsModaSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.moda.WebsModaSeccion7;
import com.colabora.soluciones.convocatoriafeyac.web.productos.WebsProductosSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.salud.WebsSaludSeccion1;
import com.colabora.soluciones.convocatoriafeyac.web.servicios.WebsServiciosSeccion1;

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

    private SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences("misDatos", 0);

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
                    if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("1")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://food.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("2")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://products.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("3")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://services.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("4")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://fashion.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("5")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://health.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("6")){
                        if(sharedPreferences.getString("nombrePagWeb","").length() > 0){
                            String url = "http://apps.solucionescolabora.com/u/" + sharedPreferences.getString("nombrePagWeb", "");
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            startActivity(i);
                        }
                    }
                    return true;
                }
                else if(id == R.id.action_editar_mi_pag){
                    if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("1")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsComidaSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("2")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsProductosSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("3")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsServiciosSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("4")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsModaSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("5")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebsSaludSeccion1.class);
                        startActivity(i);
                    }
                    else if(sharedPreferences.getString("tipo_mi_pag_web", "").equals("6")){
                        Intent i = new Intent(MenuPagWebActivity.this, WebAppsSeccion1Activity.class);
                        startActivity(i);
                    }
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
