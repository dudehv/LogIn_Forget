package com.example.login_forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newpass1 extends AppCompatActivity {
    EditText newpass1,newpass2;
    Button Confpass;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpass1);
        newpass1=findViewById(R.id.newpass1);
        newpass2=findViewById(R.id.newpass2);
        Confpass=findViewById(R.id.Confpass);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            userid = bundle.getString("userid");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Confpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utilites.isValPass(newpass1.getText().toString(),newpass2.getText().toString(),userid)){
                    Utilites.updatepass(newpass1.getText().toString(),userid, com.example.login_forget.newpass1.this);
                }
                startActivity(new Intent(getApplication(),LogIN.class));
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}