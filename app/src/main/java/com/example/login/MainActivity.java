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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button login;
    private TextView register ;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.etnumber);
        password=(EditText)findViewById(R.id.etPassword);
        login=(Button) findViewById(R.id.button);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        register = (TextView)findViewById(R.id.tvregister);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view){
                validate(name.getText().toString(),password.getText().toString());

            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });

    }
    private void validate( String number , String password){
            firebaseAuth.signInWithEmailAndPassword(number,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,second.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }
}