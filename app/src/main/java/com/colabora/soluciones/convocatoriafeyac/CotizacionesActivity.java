package com.colabora.soluciones.convocatoriafeyac;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.colabora.soluciones.convocatoriafeyac.Db.Querys;
import com.colabora.soluciones.convocatoriafeyac.Modelos.Conceptos;
import com.colabora.soluciones.convocatoriafeyac.Modelos.Cotizacion;

import java.util.ArrayList;
import java.util.List;

public class CotizacionesActivity extends AppCompatActivity {

    // *************************** RECYCLER VIEW ************************

    private class DataConfigHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNotas;
        private TextView txtMonto;
        private TextView txtFecha;

        private List<Cotizacion> cotizaciones = new ArrayList<Cotizacion>();
        private Context ctx;

        public DataConfigHolder(View itemView, Context ctx, final List<Cotizacion> cotizaciones) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.cotizaciones = cotizaciones;
            this.ctx = ctx;

            txtNotas = (TextView) itemView.findViewById(R.id.item_cotizacion_notas);
            txtFecha = (TextView) itemView.findViewById(R.id.item_cotizacion_fecha);
            txtMonto = (TextView) itemView.findViewById(R.id.item_cotizacion_monto);

        }

        public void bindConfig(final Cotizacion cotizacion) {
            txtNotas.setText(cotizacion.getNotasAdmin());
            txtFecha.setText(cotizacion.getFecha());
            txtMonto.setText(cotizacion.getTotal());
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

        }

    }


    private class DataConfigAdapter extends RecyclerView.Adapter<CotizacionesActivity.DataConfigHolder> {

        private List<Cotizacion> cotizaciones;
        Context ctx;

        public DataConfigAdapter(List<Cotizacion> cotizaciones, Context ctx ){
            this.cotizaciones = cotizaciones;
            this.ctx = ctx;
        }

        @Override
        public CotizacionesActivity.DataConfigHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_cotizacion, parent, false);
            return new CotizacionesActivity.DataConfigHolder(view, ctx, cotizaciones);
        }

        @Override
        public void onBindViewHolder(final CotizacionesActivity.DataConfigHolder holder, final int position) {
            holder.bindConfig(cotizaciones.get(position));

        }

        @Override
        public int getItemCount() {
            return cotizaciones.size();
        }

    }

    private CotizacionesActivity.DataConfigAdapter adapter;
    private List<Cotizacion> cotizaciones = new ArrayList<Cotizacion>();
    private RecyclerView recyclerView;

    // ******************************************************************



    private FloatingActionButton floatingActionButton;
    private Querys querys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizaciones);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.fabAddCotizacion);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerCotizaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CotizacionesActivity.this, NuevaCotizacionActivity.class);
                startActivity(i);
            }
        });

        querys = new Querys(getApplicationContext());
        cotizaciones = querys.getAllCotizaciones();
        
        // *********** LLENAMOS EL RECYCLER VIEW *****************************
        adapter = new CotizacionesActivity.DataConfigAdapter(cotizaciones, getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}
