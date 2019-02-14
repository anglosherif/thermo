package com.example.thermonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView SignUp;
    private EditText TextEmail;
    private EditText TextPassword;
    private Button login;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser() !=null){
            finish();
            Intent p=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(p);
        }
        TextEmail =(EditText) findViewById(R.id.editText11);
        TextPassword =(EditText) findViewById(R.id.editText12);
        login=(Button)findViewById(R.id.button);
        SignUp=(TextView)findViewById(R.id.textView3);
        progressDialog=new ProgressDialog(this);
        SignUp.setOnClickListener(this);
      login.setOnClickListener(this);

    }
    public void userLogin(){
        String email=TextEmail.getText().toString().trim();
        String password=TextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"please enter Email",Toast.LENGTH_SHORT).show();
            return;
        } if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;}
            progressDialog.setMessage("logging in...");
        progressDialog.show();
auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
      progressDialog.dismiss();
      if (task.isSuccessful()){
          finish();
          Intent o=new Intent(MainActivity.this,Main2Activity.class);
          startActivity(o);      }
    }
});

    }
    @Override
    public void onClick (View view){
        if (view==login){
            userLogin();
        }
if (view==SignUp){
    finish();
    Intent j=new Intent(MainActivity.this,RegisterActivity.class);
    startActivity(j);
}
    }
}
