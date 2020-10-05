package com.rku.tutorial11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import static com.rku.tutorial11.MyUtil.jsonArray;


public class UserData extends AppCompatActivity {
    TextView txtId,txtName,txtUsername,txtEmail,txtStreet,txtSuite,txtCity,txtZipCode,txtLat,txtLng,txtPhone,txtWebsite,txtCompanyName,txtCatchPhrase,txtBs;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        setTitle("User Detail's");

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtUsername = findViewById(R.id.txtUserName);
        txtEmail = findViewById(R.id.txtEmail);
        txtStreet = findViewById(R.id.txtStreet);
        txtSuite = findViewById(R.id.txtSuite);
        txtCity = findViewById(R.id.txtCity);
        txtZipCode = findViewById(R.id.txtZipCode);
        txtLat = findViewById(R.id.txtLat);
        txtLng = findViewById(R.id.txtLng);
        txtPhone = findViewById(R.id.txtPhone);
        txtWebsite = findViewById(R.id.txtWebsite);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtCatchPhrase = findViewById(R.id.txtCatchPhrase);
        txtBs = findViewById(R.id.txtBs);


        Intent intent = getIntent();
        int i = intent.getIntExtra("userdata",0);
        int id = intent.getIntExtra("UserData",0);

        try {
            JSONObject userObj = jsonArray.getJSONObject(i);

            txtId.setText("ID : "+ userObj.getString("id"));
            txtName.setText("Name : "+ userObj.getString("name"));
            txtUsername.setText("Username : "+ userObj.getString("username"));
            txtEmail.setText("Email : "+ userObj.getString("email"));

            JSONObject addObj = userObj.getJSONObject("address");
            txtStreet.setText("Street : "+ addObj.getString("street"));
            txtSuite.setText("Suite : "+ addObj.getString("suite"));
            txtCity.setText("City : "+ addObj.getString("city"));
            txtZipCode.setText("ZipCode : "+ addObj.getString("zipcode"));

            JSONObject addObj1 = addObj.getJSONObject("geo");
            txtLat.setText("Lat : "+ addObj1.getString("lat"));
            txtLng.setText("Lng : "+ addObj1.getString("lng"));


            txtPhone.setText("Phone : "+ userObj.getString("phone"));
            txtWebsite.setText("Website : "+ userObj.getString("website"));

            JSONObject addObj2 = userObj.getJSONObject("company");
            txtCompanyName.setText("Name : "+ addObj2.getString("name"));
            txtCatchPhrase.setText("CatchPhrase : "+ addObj2.getString("catchPhrase"));
            txtBs.setText("Bs : "+ addObj2.getString("bs"));

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}