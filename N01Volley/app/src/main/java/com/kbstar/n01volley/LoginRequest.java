package com.kbstar.n01volley;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
   private static String URL = "http://10.5.5.200:8080/android/login.php";
   private Map<String, String> map;

    public LoginRequest(String id,
                        String pass,
                        int method,
                        Response.Listener<String> listener,
                        @Nullable Response.ErrorListener errorListener) {
        super(method, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("id", id);
        map.put("pass", pass);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        /*
            postParam = "&id="+id + "&pass=" + pass;
         */
        return map;
    }
}
