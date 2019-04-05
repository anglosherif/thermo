package com.example.thermonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Signup;
    private EditText EmailReg;
    private EditText passwordReg;
    //private EditText UserReg;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Signup=(Button)findViewById(R.id.button2);
        EmailReg=(EditText)findViewById(R.id.editText3);
        passwordReg=(EditText)findViewById(R.id.editText2);
      //  UserReg=(EditText)findViewById(R.id.editText);
        Signup.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
    }
    private void UserReg(){
        final String mail=EmailReg.getText().toString().trim();
        String pass=passwordReg.getText().toString().trim();
      //  String user=UserReg.getText().toString().trim();

        if (TextUtils.isEmpty(mail)){
            Toast.makeText(this,"please enter Email",Toast.LENGTH_SHORT).show();
            return;
        } if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;}

        progressDialog.setMessage("regestering...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                 //   Log.w(mail, "signInWithEmail:failed", task.getException());
                    progressDialog.hide();
                    Toast.makeText(RegisterActivity.this, "User Authentication: ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,wifidetect.class));

                }
                else
                    Toast.makeText(RegisterActivity.this, "Authentication failed:" +
                            task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v==Signup){
            UserReg();
        }


    }
}
