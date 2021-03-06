package com.example.dbandroid;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://127.0.0.1/dbandroid/register.php";
    private Map<String,String> params;
    public RegisterRequest(String nombre, String usuario, String contrasena, String edad, Response.Listener<String> listener){
        super (Method.POST, REGISTER_REQUEST_URL, listener,null);
        params = new HashMap<>();
        params.put("nombre",nombre);
        params.put("usuario",usuario);
        params.put("contrasena",contrasena);
        params.put("edad",edad+"");
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    } }

