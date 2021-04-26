package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class register extends AppCompatActivity {
        private EditText name,password,number;
        private Button reg;
        private TextView login;
        private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupui();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    String user_num=number.getText().toString().trim();
                    String user_pass=password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_num,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(register.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,MainActivity.class));
            }
        });

    }
    private void setupui(){
        name=(EditText)findViewById(R.id.tvname);
        password=(EditText)findViewById((R.id.etPassword));
        number=(EditText)findViewById(R.id.etnum);
        reg=(Button)findViewById(R.id.btregister);
        login=(TextView)findViewById(R.id.rgdone);
        firebaseAuth=FirebaseAuth.getInstance();

    }
    private Boolean validate(){
        Boolean result=false;
        String n=name.getText().toString();
        String pass=password.getText().toString();
        String num=number.getText().toString();
        if(n.isEmpty() || pass.isEmpty() || num.isEmpty()){
            Toast.makeText(this,"Fill all the details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result;
    }
}