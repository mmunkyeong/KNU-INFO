package com.example.knu_info;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.knu_info.server.KnuInfoServer;

import java.util.HashMap;
import java.util.Map;
public class JoinRequest extends StringRequest {
    final static private String URL = KnuInfoServer.server+"/knuinfo/Register.php";
    private Map<String, String> map;


    public JoinRequest(String userID, String userPassword, String email, String phoneNumber, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPassword", userPassword);
        map.put("email", email);
        map.put("phoneNumber", phoneNumber + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

