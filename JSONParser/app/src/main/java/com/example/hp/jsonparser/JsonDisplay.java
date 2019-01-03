package com.example.hp.jsonparser;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDisplay extends AppCompatActivity {

    private static final String TAG = "";
    List<Users> usersList;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_display);

        textView=(TextView)findViewById(R.id.txtresult);
        usersList=new ArrayList<>();

        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        //String url = "http://192.168.1.6/slimapp/public/index.php/api/customers";
        String url = "http://www.mocky.io/v2/5b93df7532000052007a6638";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //ERROR DOSEN'T SET TEXT HERE
                        //textView.setText(response.toString());
                        try {
                            JSONObject obj=new JSONObject(String.valueOf(response));

                            JSONArray usersArray=obj.getJSONArray("users");
                            for (int i=0;i< usersArray.length();i++)
                            {
                                JSONObject userObject=usersArray.getJSONObject(i);
                                Users users=new Users(userObject.getInt("id"),userObject.getString("name"),
                                        userObject.getString("email"),userObject.getString("gender"));

                                usersList.add(users);
                            }

                            textView.setText("");
                            for (int j = 0; j < usersList.size(); j++){
                                textView.append("\n" + usersList.get(j) + "\n");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
