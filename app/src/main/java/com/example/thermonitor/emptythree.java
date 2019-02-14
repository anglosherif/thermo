package com.example.thermonitor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class emptythree extends AppCompatActivity implements View.OnClickListener {
private Button logout;
private FirebaseAuth firebaseAuth;
//FirebaseUser user=
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptythree);
firebaseAuth= FirebaseAuth.getInstance();
if (firebaseAuth.getCurrentUser()==null){
    finish();
    startActivity(new Intent(emptythree.this,MainActivity.class));
}
FirebaseUser user=firebaseAuth.getCurrentUser();
logout=(Button)findViewById(R.id.button3);
logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==logout){
           firebaseAuth.signOut();
           finish();
                    startActivity(new Intent(emptythree.this,MainActivity.class));

        }
    }
}
