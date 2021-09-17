package com.example.dbandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException; import org.json.JSONObject;


public class Registro extends AppCompatActivity {
    EditText etNombre, etUsuario, etContrasena, etEdad;
    Button btRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etUsuario =(EditText)findViewById(R.id.etUsuario);
        etContrasena = (EditText)findViewById(R.id.etContrasena);
        etEdad = (EditText)findViewById(R.id.etEdad);

        btRegistrar = (Button)findViewById(R.id.btRegistrar);
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombre = etNombre.getText().toString();
                final String usuario = etUsuario.getText().toString();
                final String contrasena = etContrasena.getText().toString();
                final String edad = etEdad.getText().toString();

                Response.Listener<String> respoListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                Intent intent = new Intent(Registro.this, MainActivity.class);
                                Registro.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                                builder.setMessage("Error registro")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(nombre,usuario,contrasena,edad,respoListener);
                RequestQueue queue = Volley.newRequestQueue(Registro.this);
                queue.add(registerRequest);
            }
        });
    }

}

