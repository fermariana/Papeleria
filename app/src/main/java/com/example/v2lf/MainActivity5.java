package com.example.v2lf;

import androidx.appcompat.app.AppCompatActivity;
import kotlin.random.Random;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Volley es una biblioteca HTTP que facilita y agiliza el uso de redes en apps para Android
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;//Es una clase de colección basada en mapas que se utiliza para almacenar
// pares de clave y valor, permitiendo valores nulos pero no tiene un orden.
import java.util.Map; //Es una interfaz que representa una asignación entre una clave y un valor.

public class MainActivity5 extends AppCompatActivity {

    EditText Nombre, ncelular, direccion, ciudad, estado, email, contraseña;
    Button registrarse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Nombre = (EditText) findViewById(R.id.Nombre);
        ncelular = (EditText) findViewById(R.id.ncelular);
        direccion = (EditText) findViewById(R.id.direccion);
        ciudad = (EditText) findViewById(R.id.ciudad);
        estado = (EditText) findViewById(R.id.estado);
        email = (EditText) findViewById(R.id.email);
        contraseña = (EditText) findViewById(R.id.contraseña);
        registrarse= (Button)findViewById(R.id.registrarse);

        registrarse.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v) {
                 ejecutarServicio("http://192.168.0.25/Papeleria/insertar_cliente.php");
            //Mandamos a llamar al método ejecutarServicio y mandamos la url del servicio php

             }
         }
        );

    }

    private void ejecutarServicio(String URL) {
        //Declara una peticion
        StringRequest stringRequest = new StringRequest(Request.Method.POST
                , URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operacion exitosa", Toast.LENGTH_LONG).show();
                //Mensaje que se manda cuando se logro la conexión
            }


        }, new Response.ErrorListener() {//En caso de que algo salio mal se usa este método
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {


            //Indicamos los parametros que vamos a enviar

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                //Tiene que estar en el mismo orden que en el archivo .php
                //Los que estan entre comillas deben de estar escritos igual que en el archivo .php
                //Con el método puto ingresamos los valores a enviar
                parametros.put("Nombre_C", Nombre.getText().toString());
                parametros.put("Numero_TelC", ncelular.getText().toString());
                parametros.put("Direccion_C", direccion.getText().toString());
                parametros.put("Ciudad_C", ciudad.getText().toString());
                parametros.put("Estado_C", estado.getText().toString());
                parametros.put("Contraseña", contraseña .getText().toString());
                parametros.put("Email_C", email .getText().toString());

                return parametros; //Regresa los parametros
            }
        };


        //Creamos la instancia
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Esta nos ayudara a procesar todas las peticiones echas en nuestra app
        requestQueue.add(stringRequest);
    }
}