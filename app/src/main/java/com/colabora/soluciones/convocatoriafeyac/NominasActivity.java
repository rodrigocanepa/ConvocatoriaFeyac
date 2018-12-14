package com.colabora.soluciones.convocatoriafeyac;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class NominasActivity extends AppCompatActivity {

    private EditText editSalario;
    private TextInputEditText editAguinaldo;
    private TextInputEditText editPrimaVacacional;
    private TextInputEditText editDiasVacaciones;
    private Button btnCotizar;
    private RadioButton radioMinimo;
    private RadioButton radioBajo;
    private RadioButton radioMedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominas);

        editAguinaldo = (TextInputEditText)findViewById(R.id.editNominaDiasAguinaldo);
        editDiasVacaciones = (TextInputEditText)findViewById(R.id.editNominaDiasVacaciones);
        editPrimaVacacional = (TextInputEditText)findViewById(R.id.editNominaPrimaVacacional);
        editSalario = (EditText)findViewById(R.id.editNominaSalarioDiario);
        btnCotizar = (Button)findViewById(R.id.btnCalcularNomina);
        radioBajo = (RadioButton)findViewById(R.id.radioNominaBajo);
        radioMinimo = (RadioButton)findViewById(R.id.radioNominaMinimo);
        radioMedio = (RadioButton)findViewById(R.id.radioNominaMedio);

        editDiasVacaciones.setText("6");
        editPrimaVacacional.setText("25");
        editAguinaldo.setText("15");

        editPrimaVacacional.setEnabled(false);
        radioMinimo.setChecked(true);

        radioMinimo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radioBajo.setChecked(false);
                    radioMedio.setChecked(false);
                }

            }
        });

        radioMedio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radioBajo.setChecked(false);
                    radioMinimo.setChecked(false);
                }

            }
        });


        radioBajo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    radioMinimo.setChecked(false);
                    radioMedio.setChecked(false);
                }

            }
        });


        btnCotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diasSalario = editSalario.getText().toString();
                String aguinaldo = editAguinaldo.getText().toString();
                String diasVacaciones = editDiasVacaciones.getText().toString();
                String primaVacacional = editPrimaVacacional.getText().toString();
                double riesgo = 0;

                if(diasSalario.isEmpty()){
                    editSalario.setError("Introduce un valor");
                    return;
                }
                if(aguinaldo.isEmpty()){
                    editAguinaldo.setError("Introduce un valor");
                    return;
                }
                if(diasVacaciones.isEmpty()){
                    editDiasVacaciones.setError("Introduce un valor");
                    return;
                }

                if(radioBajo.isChecked()){
                    riesgo = 0.0113065;
                }
                if(radioMedio.isChecked()){
                    riesgo = 0.025984;
                }
                if(radioMinimo.isChecked()){
                    riesgo = 0.0054355;
                }

                double diasSalario_ = Double.valueOf(diasSalario);
                double aguinaldo_ = Double.valueOf(aguinaldo);
                double diasVacaciones_ = Double.valueOf(diasVacaciones);

                if(diasSalario_ < 88.36 || diasSalario_ > 1200){
                    Toast.makeText(getApplicationContext(), "No puedes ingresar un salario diario menor al salario mínimo el cual es $88.36 o mayor a $1200.00", Toast.LENGTH_LONG).show();
                    return;
                }
                if(diasVacaciones_ < 6 || diasVacaciones_ > 30){
                    Toast.makeText(getApplicationContext(), "No puedes asignar menos de 6 días de vacaciones ni más de 30", Toast.LENGTH_LONG).show();
                    return;
                }
                if(aguinaldo_ < 15 || aguinaldo_ > 60){
                    Toast.makeText(getApplicationContext(), "No puedes asignar menos de 15 días de aguinaldo ni más de 60", Toast.LENGTH_LONG).show();
                    return;
                }

                calcularNomina(riesgo, diasSalario_, aguinaldo_, diasVacaciones_);
            }
        });

    }

    private void calcularNomina(double riesgo, double saladioNominal, double diasAguinaldo, double diasVacaciones){

        double primaVacacional = 0;
        double factorPrimaVacacional = 0;
        double factorAguinaldo = 0;
        double salarioBaseCotizacion = 0;

        double prestacionesDinero = 0;
        double prestacionesEspecieCuotaFija = 0;
        double prestacionesEspecieCuotaAdicional = 0;
        double prestacionesPensionadosBeneficiarios = 0;
        double invalidez = 0;
        double guarderia = 0;
        double riegoTrabajo = 0;

        double seguroRetiro = 0;
        double cesantia = 0;

        double infonavit;

        double ISN = 0;

        primaVacacional = diasVacaciones * 0.25;
        factorPrimaVacacional = primaVacacional/365;
        factorAguinaldo = diasAguinaldo/365;
        salarioBaseCotizacion = saladioNominal + (factorPrimaVacacional * saladioNominal) + (factorAguinaldo * saladioNominal);

        double aguinaldoPrimaVacacional = (salarioBaseCotizacion - saladioNominal) * 30;

        riegoTrabajo = riesgo * salarioBaseCotizacion * 30;
        prestacionesPensionadosBeneficiarios = 0.0105 * salarioBaseCotizacion * 30;
        prestacionesEspecieCuotaFija = 0.204 * 88.36 * 30;
        invalidez = 0.0175 * salarioBaseCotizacion * 30;
        seguroRetiro = 0.02 * salarioBaseCotizacion * 30;
        cesantia = 0.0315 * salarioBaseCotizacion * 30;
        guarderia = 0.01 * salarioBaseCotizacion * 30;
        infonavit = 0.05 * salarioBaseCotizacion * 30;
        prestacionesDinero = 0.007 * salarioBaseCotizacion * 30;

        if(salarioBaseCotizacion > 3 * 88.36){
            prestacionesEspecieCuotaAdicional = (salarioBaseCotizacion - (3*88.36)) * (30 * 0.011);
        }

        ISN = 0.02 * salarioBaseCotizacion * 30;

        double totalIMSS = riegoTrabajo + prestacionesDinero + prestacionesEspecieCuotaAdicional + prestacionesEspecieCuotaFija + prestacionesPensionadosBeneficiarios + invalidez + guarderia + seguroRetiro + cesantia + infonavit;
        double total = aguinaldoPrimaVacacional + ISN + totalIMSS + (saladioNominal * 30);

        String respuesta = "";
        respuesta += "El salario mensual de su trabajador sería de $" + String.format("%.2f", saladioNominal * 30);
        respuesta += "\nEl salario real invertido por la empresa sería de $" + String.format("%.2f", total);
        respuesta += "\n\nLa prestaciones se dividen en los siguientes conceptos:";
        respuesta += "\nAguinaldo y prima vacacional $" + String.format("%.2f", aguinaldoPrimaVacacional);
        respuesta += "\nImpuesto sobre nómina $" + String.format("%.2f", ISN);
        respuesta += "\nIMSS e INFONAVIT $" + String.format("%.2f", totalIMSS);

        AlertDialog.Builder builder = new AlertDialog.Builder(NominasActivity.this);
        builder.setTitle("Costo del empleado");
        builder.setMessage(respuesta)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });                    // Create the AlertDialog object and return it
        builder.create();
        builder.show();

    }
}
