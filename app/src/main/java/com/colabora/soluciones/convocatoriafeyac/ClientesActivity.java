package com.colabora.soluciones.convocatoriafeyac;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.colabora.soluciones.convocatoriafeyac.Modelos.Cliente;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ClientesActivity extends AppCompatActivity {


    // *************************** RECYCLER VIEW ************************

    private class DataConfigHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNombre;
        private TextView txtDescripcion;
        private TextView txtCorreo;
        private TextView txtDireccion;
        private TextView txtTelefono;
        private TextView txtHorario;

        private Button btnEditar;
        private Button btnEliminar;

        private List<Cliente> clientes = new ArrayList<Cliente>();
        private Context ctx;

        public DataConfigHolder(View itemView, Context ctx, final List<Cliente> clientes) {
            super(itemView);
            itemView.setOnClickListener(this);

            this.clientes = clientes;
            this.ctx = ctx;

            txtNombre = (TextView) itemView.findViewById(R.id.item_cliente_nombre);
            txtDescripcion = (TextView) itemView.findViewById(R.id.item_cliente_descripcion);
            txtCorreo = (TextView) itemView.findViewById(R.id.item_cliente_correo);
            txtDireccion = (TextView) itemView.findViewById(R.id.item_cliente_direccion);
            txtTelefono = (TextView) itemView.findViewById(R.id.item_cliente_telefono);
            txtHorario = (TextView) itemView.findViewById(R.id.item_cliente_horario);

            btnEditar = (Button)itemView.findViewById(R.id.btnItemClientesEditar);
            btnEliminar = (Button)itemView.findViewById(R.id.btnItemClientesEliminar);

            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void bindConfig(final Cliente cliente) {
            txtNombre.setText(cliente.getNombre());
            txtDescripcion.setText(cliente.getDescripcion());
            txtCorreo.setText(cliente.getCorreo());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtHorario.setText(cliente.getHorario());
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

        }

    }


    private class DataConfigAdapter extends RecyclerView.Adapter<ClientesActivity.DataConfigHolder> {

        private List<Cliente> clientes;
        Context ctx;

        public DataConfigAdapter(List<Cliente> clientes, Context ctx ){
            this.clientes = clientes;
            this.ctx = ctx;
        }

        @Override
        public ClientesActivity.DataConfigHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_clientes, parent, false);
            return new ClientesActivity.DataConfigHolder(view, ctx, clientes);
        }

        @Override
        public void onBindViewHolder(final ClientesActivity.DataConfigHolder holder, final int position) {
            holder.bindConfig(clientes.get(position));

        }

        @Override
        public int getItemCount() {
            return clientes.size();
        }

    }

    private ClientesActivity.DataConfigAdapter adapter;
    private List<Cliente> clientes = new ArrayList<Cliente>();
    // ******************************************************************


    private Button btnClientes;
    private Button btnProveedores;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    private boolean clientes_ = false;
    private boolean proveedores_ = false;

    private TextInputEditText editDialogNombre;
    private TextInputEditText editDialogDescripcion;
    private TextInputEditText editDialogCorreo;
    private TextInputEditText editDialogDireccion;
    private TextInputEditText editDialogTelefono;
    private TextInputEditText editDialogHorario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        btnClientes = (Button)findViewById(R.id.btnClientesClientes);
        btnProveedores = (Button) findViewById(R.id.btnClientesProveedores);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerProveedores);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fabAddClientes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        proveedores_ = true;
        clientes_ = false;

        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClientes.setBackgroundColor(Color.parseColor("#82a33b"));
                btnProveedores.setBackgroundColor(Color.LTGRAY);
                clientes_ = true;
                proveedores_ = false;

            }
        });

        btnProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnProveedores.setBackgroundColor(Color.parseColor("#82a33b"));
                btnClientes.setBackgroundColor(Color.LTGRAY);
                proveedores_ = true;
                clientes_ = false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clientes_){
                    Toast.makeText(getApplicationContext(), "clientes", Toast.LENGTH_SHORT).show();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(ClientesActivity.this);

                    // Get the layout inflater
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View formElementsView = inflater.inflate(R.layout.dialog_add_cliente,
                            null, false);

                    editDialogNombre = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogClienteNombre);
                    editDialogDescripcion = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogClienteDescripcion);
                    editDialogCorreo = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogClienteCorreo);
                    editDialogDireccion = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogClienteDireccion);
                    editDialogTelefono = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogClienteTelefono);
                    editDialogHorario = (TextInputEditText) formElementsView.findViewById(R.id.txtDialogClienteHorario);


                    builder.setTitle("Cliente");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String nombre = editDialogNombre.getText().toString();
                            String descripcion = editDialogDescripcion.getText().toString();
                            String correo = editDialogCorreo.getText().toString();
                            String direccion = editDialogDireccion.getText().toString();
                            String telefono = editDialogTelefono.getText().toString();
                            String horario = editDialogHorario.getText().toString();

                            if(nombre.length() < 1){
                                Toast.makeText(getApplicationContext(), "Debes llenar los datos obligatorios para guardar", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(descripcion.length() < 2){
                                Toast.makeText(getApplicationContext(), "Debes llenar los datos obligatorios para guardar", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(correo.length() < 2){
                                Toast.makeText(getApplicationContext(), "Debes llenar los datos obligatorios para guardar", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(direccion.length() == 0){
                                direccion = "No registrado";
                            }
                            if(telefono.length() == 0){
                                telefono = "No registrado";
                            }
                            if(horario.length() == 0){
                                horario = "No registrado";
                            }

                            Cliente cliente = new Cliente(0,nombre,descripcion,correo,direccion,telefono,horario);
                            clientes.add(cliente);
                            // *********** LLENAMOS EL RECYCLER VIEW *****************************
                            adapter = new ClientesActivity.DataConfigAdapter(clientes, getApplicationContext());
                            recyclerView.setAdapter(adapter);


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

                if(proveedores_){
                    Toast.makeText(getApplicationContext(), "proveedores", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
