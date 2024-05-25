package com.example.termiar.Formulario;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.R;

public class Formulario_Registro extends AppCompatActivity {
    private EditText etUsuario, etTalla, etPeso, etFechaUltimaDonacion;
    private Spinner spinnerSexo, spinnerTipoSangre;
    private CheckBox cbImpedimentos, cbAceptoTerminos;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_registro);

        etUsuario = findViewById(R.id.et_usuario);
        etTalla = findViewById(R.id.et_talla);
        etPeso = findViewById(R.id.et_peso);
        etFechaUltimaDonacion = findViewById(R.id.et_fecha_ultima_donacion);
        spinnerSexo = findViewById(R.id.spinner_sexo);
        spinnerTipoSangre = findViewById(R.id.spinner_tipo_sangre);
        cbImpedimentos = findViewById(R.id.cb_impedimentos);
        cbAceptoTerminos = findViewById(R.id.cb_acepto_terminos);
        btnEnviar = findViewById(R.id.btn_enviar);

        setupSpinner(spinnerSexo, R.array.sexo_options);
        setupSpinner(spinnerTipoSangre, R.array.tipo_sangre_options);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAceptoTerminos.isChecked()) {
                    // Handle form submission
                    Toast.makeText(Formulario_Registro.this, "Formulario enviado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Formulario_Registro.this, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void setupSpinner(Spinner spinner, int arrayResource) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                arrayResource,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    }