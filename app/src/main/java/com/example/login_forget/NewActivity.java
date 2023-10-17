package com.example.login_forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;






import android.widget.EditText;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {
    TextView datadisplay,userid,passwordshow;
    EditText id;
    EditText password;
    Button sharebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        datadisplay=findViewById(R.id.datadisplay);
        userid=findViewById(R.id.userid);
        id=findViewById(R.id.id);
        passwordshow=findViewById(R.id.passwordshow);
        password=findViewById(R.id.password);
        sharebtn=findViewById(R.id.sharebtn);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String userid = bundle.getString("userid");

            if (userid != null || userid != "") {
                String pass=Utilites.getPassagainstemail(userid,NewActivity.this);
                id.setText(userid);
                password.setText(pass);
            } else {
                id.setText("");
            }
        }

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"https://developer.android.com/training/sharing/send");
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent,"share it"));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}