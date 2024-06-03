package com.example.tfgtickets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnCrearTarjeta, btnEliminarTarjeta, btnCambiarTickets, btnCambiarSaldo, btnVerTarjeta;
    EditText textDireccionIP;
    public static final String DIRECCIONIP = "direccionIP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCrearTarjeta = findViewById(R.id.btnCrearTarjeta);
        btnEliminarTarjeta = findViewById(R.id.btnEliminarTarjeta);
        btnCambiarTickets = findViewById(R.id.btnCambiarTickets);
        btnCambiarSaldo = findViewById(R.id.btnCambiarSaldo);
        btnVerTarjeta = findViewById(R.id.btnVerTarjeta);
        textDireccionIP = findViewById(R.id.editTextIP);
        Intent intent = getIntent();
        String direccionIP = intent.getStringExtra(MainActivity.DIRECCIONIP);
        if (direccionIP != null) {
            textDireccionIP.setText(direccionIP);
        }
        btnCrearTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccionIP = textDireccionIP.getText().toString();
                Intent intent = new Intent(MainActivity.this, CrearTarjetaActivity.class);
                intent.putExtra(DIRECCIONIP, direccionIP);
                startActivity(intent);
            }
        }
        );
        btnEliminarTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccionIP = textDireccionIP.getText().toString();
                Intent intent = new Intent(MainActivity.this, EliminarTarjetaActivity.class);
                intent.putExtra(DIRECCIONIP, direccionIP);
                startActivity(intent);
            }
        }
        );
        btnCambiarTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccionIP = textDireccionIP.getText().toString();
                Intent intent = new Intent(MainActivity.this, CambiarTicketsActivity.class);
                intent.putExtra(DIRECCIONIP, direccionIP);
                startActivity(intent);
            }
        }
        );
        btnCambiarSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccionIP = textDireccionIP.getText().toString();
                Intent intent = new Intent(MainActivity.this, CambiarSaldoActivity.class);
                intent.putExtra(DIRECCIONIP, direccionIP);
                startActivity(intent);
            }
        }
        );
        btnVerTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String direccionIP = textDireccionIP.getText().toString();
                Intent intent = new Intent(MainActivity.this, VerTarjetaActivity.class);
                intent.putExtra(DIRECCIONIP, direccionIP);
                startActivity(intent);
            }
        }
        );
    }
}