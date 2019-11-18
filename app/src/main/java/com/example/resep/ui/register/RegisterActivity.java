package com.example.resep.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.resep.AlertDialogManager;
import com.example.resep.MainActivity;
import com.example.resep.R;
import com.example.resep.SessionManagement;
import com.example.resep.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity{

    // Email, password edittext
    EditText username, password, retypepass, email;

    // login button
    Button regis;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Session Manager
        session = new SessionManagement(getApplicationContext());

        // Email, Password input text
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        retypepass = (EditText) findViewById(R.id.retype);

        // Login button
        regis = (Button) findViewById(R.id.register);


        // Login button click event
        regis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String nama = username.getText().toString();
                String kode = password.getText().toString();
                String eemail = email.getText().toString();

//                 Check if username, password is filled
                if(nama.trim().length() > 0 && kode.trim().length() > 0 && eemail.trim().length() > 0){
//                    // For testing puspose username, password is checked with sample data
//                    // username = test
//                    // password = test
//                    if(username.equals("test") && password.equals("test")){
//
//                        // Creating user login session
//                        // For testing i am stroing name, email as follow
//                        // Use user real data
//                        session.createLoginSession("Android Hive", "anroidhive@gmail.com");
//
//                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
//
//                    }else{
//                        // username / password doesn't match
//                        alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
//                    }
                }else{
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    alert.showAlertDialog(RegisterActivity.this, "Register failed..", "Please fill the form", false);
                }
            }
        });
    }
}
