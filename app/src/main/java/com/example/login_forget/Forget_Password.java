package com.example.login_forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Forget_Password extends AppCompatActivity {
    EditText Registeredemail;
    Button setpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Registeredemail=findViewById(R.id.Registeredemail);
        setpassword=findViewById(R.id.setpassword);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String userid = bundle.getString("userid");

            if (userid != null || userid != "") {
                Registeredemail.setText(userid);
            } else {
                Registeredemail.setText("");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yourIntent = new Intent(Forget_Password.this, newpass1.class);
                Bundle b=new Bundle();
                if (Registeredemail.getText()!=null) {
                    b.putString("userid", Registeredemail.getText().toString());
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