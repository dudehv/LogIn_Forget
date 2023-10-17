package com.example.login_forget;

import static com.example.login_forget.Utilites.isValid;
import static com.example.login_forget.Utilites.showingToast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {


    TextView Register1, textView;
    EditText fullname, pass1, pass2, email;
    Button loginbtn, registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Register1 = findViewById(R.id.Register1);
        fullname = findViewById(R.id.fullname);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        email = findViewById(R.id.email);
        textView = findViewById(R.id.textView);
        loginbtn = findViewById(R.id.loginbtn);
        registerbtn = findViewById(R.id.registerbtn);


    }



    @Override
    protected void onResume() {
        super.onResume();
        fullname.setText("");
        pass1.setText("");
        pass2.setText("");
        email.setText("");
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), LogIN.class));

            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userfullname = fullname.getText().toString();
                String userpass1 = pass1.getText().toString();
                String userpass2 = pass2.getText().toString();
                String useremail = email.getText().toString();


                if (isValid(userfullname, useremail, userpass1, userpass2)) {
                    boolean issave=Utilites.saveIntosp(Register.this, userfullname, useremail, userpass1,userpass2);
                    //startActivity(new Intent(getApplication(), LogIN.class));
                    if (issave) {
                        fullname.setText("");
                        pass1.setText("");
                        pass2.setText("");
                        email.setText("");
                        startActivity(new Intent(getApplication(), LogIN.class));
                        Toast.makeText(Register.this, "Registration Succesfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    showingToast(Register.this);
                }
            }
        });

    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}




