package com.example.login_forget;

import static com.example.login_forget.Utilites.isValid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIN extends AppCompatActivity {
    EditText editTextPersonName,editTextPassword;
    Button button;
    TextView forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editTextPersonName=findViewById(R.id.editTextPersonName);
        editTextPassword=findViewById(R.id.editTextPassword);
        forgetpass=findViewById(R.id.forgetpass);
        button=findViewById(R.id.button);
    }

    @Override
    protected void onResume() {
        super.onResume();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = editTextPersonName.getText().toString();
                String userpass = editTextPassword.getText().toString();
                if (isValid(useremail, userpass)) {
                    int cridentialIsValid = Utilites.cheackAuthentication(LogIN.this, useremail, userpass);
                    switch (cridentialIsValid) {
                        case 101: {
                            Toast.makeText(LogIN.this, "Log IN Succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(), NewActivity.class));
                            Intent yourIntent = new Intent(LogIN.this, NewActivity.class);
                            Bundle b = new Bundle();
                            if (editTextPersonName.getText() != null) {
                                b.putString("userid", editTextPersonName.getText().toString());
                            }
                            yourIntent.putExtras(b);
                            startActivity(yourIntent);

                        }
                        case 102: {
                            Toast.makeText(LogIN.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case 103: {
                            Toast.makeText(LogIN.this, "You are not registered user. Please go to registration secreen", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(), Register.class));
                            break;
                        }
                        case 105: {
                            Toast.makeText(LogIN.this, "Please try again..", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }
                }



            }
        });


         forgetpass.setOnClickListener(new View.OnClickListener(){
           @Override
             public void onClick (View v){
            Intent yourIntent = new Intent(LogIN.this, Forget_Password.class);
            Bundle b = new Bundle();
               if (editTextPersonName.getText() != null) {
               b.putString("userid", editTextPersonName.getText().toString());
               }
               yourIntent.putExtras(b);
               startActivity(yourIntent);
           }
         });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}