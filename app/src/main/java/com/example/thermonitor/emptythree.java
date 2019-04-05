package com.example.thermonitor;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class emptythree extends AppCompatActivity implements View.OnClickListener {
private Button logout;

private FirebaseAuth firebaseAuth;
//DatabaseReference reference;
    private TextView readingView;
    private TextView Warning;

    Firebase myfirebase;
//FirebaseUser user=
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptythree);
        readingView=(TextView)findViewById(R.id.textView5);
        Warning=(TextView)findViewById(R.id.textView7);
Firebase.setAndroidContext(getApplicationContext());
myfirebase=new Firebase("https://anglothermolitor.firebaseio.com/"+wifidetect.ssid);
firebaseAuth= FirebaseAuth.getInstance();
if (firebaseAuth.getCurrentUser()==null){
    finish();
    startActivity(new Intent(emptythree.this,MainActivity.class));
}
    FirebaseUser user=firebaseAuth.getCurrentUser();
        //Toast.makeText(emptythree.this,wifidetect.x,Toast.LENGTH_SHORT).show();

logout=(Button)findViewById(R.id.button3);
logout.setOnClickListener(this);
  FirebaseDatabase database= FirebaseDatabase.getInstance();
DatabaseReference databaseReference=database.getReference(wifidetect.ssid);
          myfirebase.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
             String reading=dataSnapshot.getValue(String.class);
                  readingView.setText(reading);
                  if(Integer.parseInt(reading)>30){
Warning.setVisibility(View.VISIBLE);

                  }
                  else {
                      Warning.setVisibility(View.INVISIBLE);

                  }
              }

              @Override
              public void onCancelled(FirebaseError firebaseError) {

              }
          });

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
