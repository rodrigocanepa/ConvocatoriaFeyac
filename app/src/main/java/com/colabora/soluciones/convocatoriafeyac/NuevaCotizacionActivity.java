package com.colabora.soluciones.convocatoriafeyac;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.Modelos.Conceptos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NuevaCotizacionActivity extends AppCompatActivity {

    // *************************** RECYCLER VIEW ************************

    private class DataConfigHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtConcepto;
        private TextView txtCantidad;
        private TextView txtPrecio;
        private TextView txtDescuento;
        private TextView txtImpuesto;
        private Button btnEditar;
        private Button btnEliminar;

        private List<Conceptos> conceptos = new ArrayList<Conceptos>();
        private Context ctx;

        public DataConfigHolder(View itemView, Context ctx, final List<Conceptos> conceptos) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.conceptos = conceptos;
            this.ctx = ctx;

            txtConcepto = (TextView) itemView.findViewById(R.id.txtItemCotizacionConcepto);
            txtCantidad = (TextView) itemView.findViewById(R.id.txtItemCotizacionCantidad);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtItemCotizacionPrecio);
            txtDescuento = (TextView) itemView.findViewById(R.id.txtItemCotizacionDescuento);
            txtImpuesto = (TextView) itemView.findViewById(R.id.txtItemCotizacionImpuesto);
            btnEditar = (Button) itemView.findViewById(R.id.btnItemsEditar);
            btnEliminar = (Button) itemView.findViewById(R.id.btnItemsEliminar);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

        public void bindConfig(final Conceptos conceptos) {
            txtConcepto.setText(conceptos.getConceptos());
            txtCantidad.setText(conceptos.getCantidad());
            txtPrecio.setText("$" + conceptos.getPrecio());
            txtImpuesto.setText(conceptos.getImpuestos());
            txtDescuento.setText("$" + conceptos.getImporte());

        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

        }

    }


    private class DataConfigAdapter extends RecyclerView.Adapter<NuevaCotizacionActivity.DataConfigHolder> {

        private List<Conceptos> conceptos;
        Context ctx;

        public DataConfigAdapter(List<Conceptos> conceptos, Context ctx ){
            this.conceptos = conceptos;
            this.ctx = ctx;
        }

        @Override
        public NuevaCotizacionActivity.DataConfigHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.items_conceptos, parent, false);
            return new NuevaCotizacionActivity.DataConfigHolder(view, ctx, conceptos);
        }

        @Override
        public void onBindViewHolder(final NuevaCotizacionActivity.DataConfigHolder holder, final int position) {
            holder.bindConfig(conceptos.get(position));

        }

        @Override
        public int getItemCount() {
            return conceptos.size();
        }

    }

    private NuevaCotizacionActivity.DataConfigAdapter adapter;
    private List<Conceptos> conceptos = new ArrayList<Conceptos>();
    private RecyclerView recyclerView;

    // ******************************************************************

    private EditText editNoFormato;
    private EditText editFecha;
    private RadioButton radioButtonCantidad;
    private RadioButton radioButtonHoras;
    private ImageView imgLogo;
    private Button btnAgregarConcepto;
    private Switch swDescuento;
    private Switch swGastosEnvio;
    private EditText editDescuento;
    private EditText editGastosEnvio;
    private Spinner spinnerDescuento;
    private TextInputEditText txtNotasDestinatario;
    private TextInputEditText txtNotasAdmin;
    private TextInputEditText txtTerminos;

    private TextView txtSubtotal;
    private TextView txtDescuentos;
    private TextView txtIVA;
    private TextView txtTotal;

    // *******************************************************************

    private TextInputEditText txtDialogConcepto;
    private TextInputEditText txtDialogCantidad;
    private TextInputEditText txtDialogPrecio;
    private Switch swDialogImpuestos;

    private List<String> descuentos = new ArrayList<>();
    private static final String CERO = "0";
    private static final String BARRA = "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cotizacion);
        setTitle("Nueva Cotización");
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerConceptosCotizacion);
        editNoFormato = (EditText)findViewById(R.id.editCotizacionFolio);
        editFecha = (EditText)findViewById(R.id.editCotizacionFecha);
        radioButtonCantidad = (RadioButton)findViewById(R.id.radioCotizacionCantidad);
        radioButtonHoras = (RadioButton)findViewById(R.id.radioCotizacionHoras);
        imgLogo = (ImageView)findViewById(R.id.imgCotizacionLogo);
        btnAgregarConcepto = (Button)findViewById(R.id.btnCotizacionAddConcepto);
        swDescuento = (Switch)findViewById(R.id.swCotizacionDescuento);
        swGastosEnvio = (Switch)findViewById(R.id.swCotizacionGastosEnvio);
        editDescuento = (EditText)findViewById(R.id.editCotizacionDescuento);
        editGastosEnvio = (EditText)findViewById(R.id.editCotizacionGastosEnvio);
        spinnerDescuento = (Spinner)findViewById(R.id.spinnerCotizacionDescuento);
        txtNotasAdmin = (TextInputEditText)findViewById(R.id.txtCotizacionesNotasAdmin);
        txtNotasDestinatario = (TextInputEditText)findViewById(R.id.txtCotizacionNotas);
        txtTerminos = (TextInputEditText)findViewById(R.id.txtCotizacionesTerminos);

        txtSubtotal = (TextView)findViewById(R.id.txtCotizacionSubtotal);
        txtDescuentos = (TextView)findViewById(R.id.txtCotizacionDescuento);
        txtIVA = (TextView)findViewById(R.id.txtCotizacionIVA);
        txtTotal = (TextView)findViewById(R.id.txtCotizacionTotal);

        editDescuento.setEnabled(false);

        descuentos.add("%");
        descuentos.add("$");

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);

        swDescuento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editDescuento.setEnabled(true);
                }
                else{
                    editDescuento.setEnabled(false);
                    txtDescuentos.setText("-$0");
                }
            }
        });

        if(!swDescuento.isChecked()){
            txtDescuentos.setText("-$0");
        }

        editFecha.setText(today);

        editFecha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.onTouchEvent(motionEvent);
                InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                return true;
            }

        });

        editFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calendario para obtener fecha & hora
                final Calendar c = Calendar.getInstance();

                //Variables para obtener la fecha
                final int mes = c.get(Calendar.MONTH);
                final int dia = c.get(Calendar.DAY_OF_MONTH);
                final int anio = c.get(Calendar.YEAR);

                DatePickerDialog recogerFecha = new DatePickerDialog(NuevaCotizacionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                        final int mesActual = month + 1;
                        //Formateo el día obtenido: antepone el 0 si son menores de 10
                        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                        //Formateo el mes obtenido: antepone el 0 si son menores de 10
                        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                        //Muestro la fecha con el formato deseado
                        editFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


                    }
                    //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
                    /**
                     *También puede cargar los valores que usted desee
                     */
                },anio, mes, dia);
                //Muestro el widget
                recogerFecha.show();
            }
        });

        editDescuento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ****************** ACTUALIZAMOS EL SUBTOTAL ********************
                if(editDescuento.getText().toString().length() > 0){
                    double subtotal = 0;
                    double iva = 0;
                    double descuento = 0;

                    for(int j = 0; j < conceptos.size(); j++){
                        subtotal += Double.valueOf(conceptos.get(j).getImporte());
                        iva += Double.valueOf(conceptos.get(j).getImpuestos_pesos());
                    }

                    if(swDescuento.isChecked()){
                        if(spinnerDescuento.getSelectedItem().equals("$")){
                            txtDescuentos.setText("-$" + editDescuento.getText().toString());
                        }
                        else{
                            double desc = Double.valueOf(editDescuento.getText().toString());
                            double descReal = desc/100;
                            descuento = subtotal * descReal;
                            txtDescuentos.setText("-$" + String.valueOf(descuento));
                        }
                    }

                    double total = subtotal - descuento + iva;
                    txtTotal.setText("$" + String.valueOf(total));
                    txtSubtotal.setText("$" + String.valueOf(subtotal));
                    txtIVA.setText("$" + String.valueOf(iva));

                    // ****************************************************************
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<String>(
                NuevaCotizacionActivity.this, R.layout.support_simple_spinner_dropdown_item, descuentos);
        spinnerDescuento.setAdapter(adapterCategoria);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        btnAgregarConcepto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(NuevaCotizacionActivity.this);

                // Get the layout inflater
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.dialog_nuevo_concepto,
                        null, false);


                txtDialogConcepto = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogConcepto);
                txtDialogCantidad = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogCantidad);
                txtDialogPrecio = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogPrecio);
                swDialogImpuestos = (Switch) formElementsView.findViewById(R.id.swDialogImpuestos);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String concepto_ = txtDialogConcepto.getText().toString();
                        String cantidad_ = txtDialogCantidad.getText().toString();
                        String precio_ = txtDialogPrecio.getText().toString();
                        String descuento_ = "No aplica";
                        String descuento_pesos = "0";
                        String impuestos_ = "No aplica";
                        String impuestos_pesos = "0";

                        if(concepto_.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Debes llenar los campos obligatorios", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(cantidad_.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Debes llenar los campos obligatorios", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if(precio_.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Debes llenar los campos obligatorios", Toast.LENGTH_LONG).show();
                            return;
                        }

                        double cantidad_double = Double.valueOf(cantidad_);
                        double precio_double = Double.valueOf(precio_);
                        double importe_ = cantidad_double * precio_double;
                        String importe = String.valueOf(importe_);

                        if(swDialogImpuestos.isChecked()){
                            impuestos_ = "IVA: 16%";
                            double descuento_double = (cantidad_double * precio_double) * 1.16 - (cantidad_double * precio_double);
                            impuestos_pesos = String.valueOf(descuento_double);
                        }

                        Conceptos concepto = new Conceptos(concepto_,cantidad_,precio_,importe,impuestos_,impuestos_pesos);
                        conceptos.add(concepto);

                        // *********** LLENAMOS EL RECYCLER VIEW *****************************
                        adapter = new NuevaCotizacionActivity.DataConfigAdapter(conceptos, getApplicationContext());
                        recyclerView.setAdapter(adapter);

                        // ****************** ACTUALIZAMOS EL SUBTOTAL ********************
                        double subtotal = 0;
                        double iva = 0;
                        double descuento = 0;

                        for(int j = 0; j < conceptos.size(); j++){
                            subtotal += Double.valueOf(conceptos.get(j).getImporte());
                            iva += Double.valueOf(conceptos.get(j).getImpuestos_pesos());
                        }

                        if(swDescuento.isChecked()){
                            if(spinnerDescuento.getSelectedItem().equals("$")){
                                txtDescuentos.setText("-$" + editDescuento.getText().toString());
                            }
                            else{
                                double desc = Double.valueOf(editDescuento.getText().toString());
                                double descReal = desc/100;
                                descuento = subtotal * descReal;
                                txtDescuentos.setText("$" + String.valueOf(descuento));
                            }
                        }

                        double total = subtotal - descuento + iva;
                        txtTotal.setText("$" + String.valueOf(total));
                        txtSubtotal.setText("$" + String.valueOf(subtotal));
                        txtIVA.setText("$" + String.valueOf(iva));

                        // ****************************************************************

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(formElementsView);
                // Add action buttons
                builder.create();
                builder.show();
            }
        });
    }
}
