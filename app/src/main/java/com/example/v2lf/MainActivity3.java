package com.example.v2lf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Volley es una biblioteca HTTP que facilita y agiliza el uso de redes en apps para Android
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;//Es una clase de colección basada en mapas que se utiliza para almacenar
// pares de clave y valor, permitiendo valores nulos pero no tiene un orden.
import java.util.Map; //Es una interfaz que representa una asignación entre una clave y un valor.

public class MainActivity3 extends AppCompatActivity {
    EditText editTextTextPersonName, editTextTextPassword;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        button2= (Button)findViewById(R.id.button2);





        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCliente("http://192.168.0.25/Papeleria/validar_cliente.php");
                 //Mandamos a llamar al método validar cliente y mandamos la url del servicio php
            }
        });

    }
    public void Siguiente(View view)
    {
        Intent siguiente = new Intent(this, MainActivity5.class);
        startActivity(siguiente);
    }

    private void validarCliente(String URL) { //Método donde verificaremos si el cliente esta registrado o no
        StringRequest StringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                                         //Tipo de método de envio que en este caso es POST
            @Override
            public void onResponse(String response) { // Este método reaccionara cuando la petición se procese.
                if (!response.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity3.this, "Correo o contraseña incorrecta",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() { // Este método reaccionara cuando la petición no se procese por un error.
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                //Parametros que nuestro servidor solicita
                //Tiene que estar en el mismo orden que en el archivo .php
                //Los que estan entre comillas deben de estar escritos igual que en el archivo .php
                //Con el método puto ingresamos los valores a enviar
                parametros.put("Contraseña", editTextTextPassword.getText().toString());
                parametros.put("Email_C", editTextTextPersonName.getText().toString());
                return parametros;  //Devuelve los parametros

            }
        };

        //Creamos la instancia
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Esta nos ayudara a procesar todas las peticiones echas en nuestra app
        requestQueue.add(StringRequest);

    }


}







