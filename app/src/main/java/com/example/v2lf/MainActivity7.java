package com.example.v2lf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity7 extends AppCompatActivity {
    EditText editTextTextPersonName3, editTextTextPersonName4,editTextTextPersonName5,
            editTextTextPersonName6, editTextTextPersonName7
            ,editTextTextPersonName8, editTextTextPersonName9;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        editTextTextPersonName3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = (EditText) findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 = (EditText) findViewById(R.id.editTextTextPersonName5);
        editTextTextPersonName6 = (EditText) findViewById(R.id.editTextTextPersonName6);
        editTextTextPersonName7 = (EditText) findViewById(R.id.editTextTextPersonName7);
        editTextTextPersonName8 = (EditText) findViewById(R.id.editTextTextPersonName8);
        editTextTextPersonName9 = (EditText) findViewById(R.id.editTextTextPersonName9);
     factura("http://192.168.0.25/Papeleria/factura.php?IDFactura=1A");



    }

   private void factura(String URL)
   {
       JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
               {
                   JSONObject jsonObject = null;
                   for (int i = 0; i < response.length(); i++) {
                       try {
                           //Se mandan a llamar los campos para llenar la factura
                           editTextTextPersonName3.setText(jsonObject.getString("IDFactura"));
                           editTextTextPersonName4.setText(jsonObject.getString("IDCliente"));
                           editTextTextPersonName5.setText(jsonObject.getString("IDVentas"));
                           editTextTextPersonName6.setText(jsonObject.getString("FechaF"));
                           editTextTextPersonName7.setText(jsonObject.getString("SubTotal"));
                           editTextTextPersonName8.setText(jsonObject.getString("IVA"));
                           editTextTextPersonName9.setText(jsonObject.getString("Total"));
                       } catch (JSONException e) {
                           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                       }
                   }
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(getApplicationContext(),"Error de conexión :(",Toast.LENGTH_SHORT).show();
           }
       });
       requestQueue=Volley.newRequestQueue(this);
       requestQueue.add(jsonArrayRequest);
   }
}