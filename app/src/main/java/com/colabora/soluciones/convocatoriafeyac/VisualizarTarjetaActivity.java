package com.colabora.soluciones.convocatoriafeyac;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class VisualizarTarjetaActivity extends AppCompatActivity {

    private TextView txtNombre1;
    private TextView txtCargo1;
    private TextView txtTelefono1;
    private TextView txtCorreo1;
    private TextView txtPagina1;
    private TextView txtDireccion1;
    private TextView txtFacebook1;
    private TextView txtInstagram1;

    private TextView txtNombre2;
    private TextView txtCargo2;
    private TextView txtTelefono2;
    private TextView txtCorreo2;
    private TextView txtPagina2;
    private TextView txtDireccion2;
    private TextView txtFacebook2;
    private TextView txtInstagram2;

    private TextView txtNombre3;
    private TextView txtCargo3;
    private TextView txtTelefono3;
    private TextView txtCorreo3;
    private TextView txtPagina3;
    private TextView txtDireccion3;
    private TextView txtFacebook3;
    private TextView txtInstagram3;

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;

    private ImageView imgView1;
    private ImageView imgView2;

    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;

    private SharedPreferences sharedPreferences;

    private int seleccionEstiloTarjeta;
    private int seleccionTipoLetra;
    private String nombre;
    private String puesto;
    private String numero;
    private String correo;
    private String pagina;
    private String direccion;
    private String facebook;
    private String instagram;

    private Button btnGuardar;
    private Button btnCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_tarjeta);

        txtNombre1 = (TextView)findViewById(R.id.txtVisualizarTarjetaNombre1);
        txtNombre2 = (TextView)findViewById(R.id.txtVisualizarTarjetaNombre2);
        txtNombre3 = (TextView)findViewById(R.id.txtVisualizarTarjetaNombre3);

        txtCargo1 = (TextView)findViewById(R.id.txtVisualizarTarjetaCargo1);
        txtCargo2 = (TextView)findViewById(R.id.txtVisualizarTarjetaCargo2);
        txtCargo3 = (TextView)findViewById(R.id.txtVisualizarTarjetaCargo3);

        txtTelefono1 = (TextView)findViewById(R.id.txtVisualizarTarjetaTelefono1);
        txtTelefono2 = (TextView)findViewById(R.id.txtVisualizarTarjetaTelefono2);
        txtTelefono3 = (TextView)findViewById(R.id.txtVisualizarTarjetaTelefono3);

        txtCorreo1 = (TextView)findViewById(R.id.txtVisualizarTarjetaCorreo1);
        txtCorreo2 = (TextView)findViewById(R.id.txtVisualizarTarjetaCorreo2);
        txtCorreo3 = (TextView)findViewById(R.id.txtVisualizarTarjetaCorreo3);

        txtPagina1 = (TextView)findViewById(R.id.txtVisualizarTarjetaPagina1);
        txtPagina2 = (TextView)findViewById(R.id.txtVisualizarTarjetaPagina2);
        txtPagina3 = (TextView)findViewById(R.id.txtVisualizarTarjetaPagina3);

        txtDireccion1 = (TextView)findViewById(R.id.txtVisualizarTarjetaDireccion1);
        txtDireccion2 = (TextView)findViewById(R.id.txtVisualizarTarjetaDireccion2);
        txtDireccion3 = (TextView)findViewById(R.id.txtVisualizarTarjetaDireccion3);

        txtFacebook1 = (TextView)findViewById(R.id.txtVisualizarTarjetaFacebook1);
        txtFacebook2 = (TextView)findViewById(R.id.txtVisualizarTarjetaFacebook2);
        txtFacebook3 = (TextView)findViewById(R.id.txtVisualizarTarjetaFacebook3);

        txtInstagram1 = (TextView)findViewById(R.id.txtVisualizarTarjetaInstragram1);
        txtInstagram2 = (TextView)findViewById(R.id.txtVisualizarTarjetaInstragram2);
        txtInstagram3 = (TextView)findViewById(R.id.txtVisualizarTarjetaInstragram3);

        linearLayout1 = (LinearLayout) findViewById(R.id.linearVisualizacion1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearVisualizacion2);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearVisualizacion3);

        imgView1 = (ImageView) findViewById(R.id.imgVisualizarTajetaDatos);
        imgView2 = (ImageView) findViewById(R.id.imgVisualizarTarjetaDatos2);

        relativeLayout1 = (RelativeLayout) findViewById(R.id.relativeTarjetaFinalizada1);
        relativeLayout2 =(RelativeLayout) findViewById(R.id.relativeTarjetaFinalizada2);

        btnCompartir = (Button) findViewById(R.id.btnCompartirTarjeta);
        btnGuardar = (Button) findViewById(R.id.btnGuardarTarjeta);

        // Leemos la memoria para ver que tarjetas se han creado
        sharedPreferences = getSharedPreferences("misDatos", 0);
        seleccionEstiloTarjeta = sharedPreferences.getInt("tarjetaEstilo",0);
        seleccionTipoLetra = sharedPreferences.getInt("tarjetaLetra",0);
        nombre = sharedPreferences.getString("tarjetaNombre","");
        puesto = sharedPreferences.getString("tarjetaCargo","");
        numero = sharedPreferences.getString("tarjetaNumero","");
        correo = sharedPreferences.getString("tarjetaCorreo","");
        pagina = sharedPreferences.getString("tarjetaPagina","");
        direccion = sharedPreferences.getString("tarjetaDireccion","");
        facebook = sharedPreferences.getString("tarjetaFacebook","");
        instagram = sharedPreferences.getString("tarjetaInstagram","");

        linearLayout1.setVisibility(View.INVISIBLE);
        linearLayout2.setVisibility(View.INVISIBLE);
        linearLayout3.setVisibility(View.INVISIBLE);

        if(seleccionEstiloTarjeta == 1){
            linearLayout1.setVisibility(View.VISIBLE);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.detras_1).into(imgView1);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.frente1).into(imgView2);

            txtNombre1.setText(nombre);
            txtCargo1.setText(puesto);
            txtTelefono1.setText(numero);
            txtCorreo1.setText(correo);
            txtPagina1.setText(pagina);
            txtDireccion1.setText(direccion);
            txtFacebook1.setText(facebook);
            txtInstagram1.setText(instagram);
        }

        if(seleccionEstiloTarjeta == 2){
            linearLayout1.setVisibility(View.VISIBLE);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.detras2).into(imgView1);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.frente2).into(imgView2);

            txtNombre1.setText(nombre);
            txtCargo1.setText(puesto);
            txtTelefono1.setText(numero);
            txtCorreo1.setText(correo);
            txtPagina1.setText(pagina);
            txtDireccion1.setText(direccion);
            txtFacebook1.setText(facebook);
            txtInstagram1.setText(instagram);
        }

        if(seleccionEstiloTarjeta == 3){
            linearLayout2.setVisibility(View.VISIBLE);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.detras3).into(imgView1);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.frente3).into(imgView2);

            txtNombre2.setText(nombre);
            txtCargo2.setText(puesto);
            txtTelefono2.setText(numero);
            txtCorreo2.setText(correo);
            txtPagina2.setText(pagina);
            txtDireccion2.setText(direccion);
            txtFacebook2.setText(facebook);
            txtInstagram2.setText(instagram);
        }

        if(seleccionEstiloTarjeta == 4){
            linearLayout3.setVisibility(View.VISIBLE);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.detras4).into(imgView1);
            Picasso.with(VisualizarTarjetaActivity.this).load(R.drawable.frente4).into(imgView2);

            txtNombre3.setText(nombre);
            txtCargo3.setText(puesto);
            txtTelefono3.setText(numero);
            txtCorreo3.setText(correo);
            txtPagina3.setText(pagina);
            txtDireccion3.setText(direccion);
            txtFacebook3.setText(facebook);
            txtInstagram3.setText(instagram);
        }


    }
}
