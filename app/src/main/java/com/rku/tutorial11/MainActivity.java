package com.rku.tutorial11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import static com.rku.tutorial11.MyUtil.URL_USER;

public class MainActivity extends AppCompatActivity {
    ListView lstData;
    CustomAdapter adapter;
    ProgressDialog dialog;

    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstData = findViewById(R.id.lstData);
        dialog = new ProgressDialog(MainActivity.this);

        valleyAPICall();

        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(MainActivity.this, UserData.class);
                intent.putExtra("userdata", i);
                startActivity(intent);

            }
        });
    }

    private void valleyAPICall() {
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL_USER,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                            MyUtil.jsonArray = response;
                            adapter = new CustomAdapter(MainActivity.this);
                            lstData.setAdapter(adapter);

                            if (dialog.isShowing())dialog.dismiss();

                            Log.i("Response", String.valueOf(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.toString());
                    }
                }
        );
        dialog.show();
        requestQueue.add(jsonArrayRequest);
    }

}
