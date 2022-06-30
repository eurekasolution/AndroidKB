package com.kbstar.n01volley;

import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistRequest extends StringRequest {
   private static String URL = "http://10.5.5.200:8080/android/insert.php";
   private Map<String, String> map;

    public RegistRequest(String id,
                         String name,
                         String pass,
                         int method,
                         Response.Listener<String> listener,
                         @Nullable Response.ErrorListener errorListener) {
        super(method, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("pass", pass);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        /*
            postParam = "name="+name + "&id="+id + "&pass=" + pass;
         */
        return map;
    }
}
