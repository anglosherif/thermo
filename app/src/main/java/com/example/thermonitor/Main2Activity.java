package com.example.thermonitor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class Main2Activity extends AppCompatActivity {
ListView listView;
ArrayAdapter<String>adapter;
String [] android_version={ "messi","xavi","iniesta","puyol","ronaldinho","alves","Etoo","valdes","Abidal","busquets","Henry","Maequez","YaYa"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView=(ListView)findViewById(R.id.list_view);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android_version);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent j=new Intent(Main2Activity.this,emptythree.class);
                startActivity(j);
            }
        });
    }
}
