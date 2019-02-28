package com.example.thermonitor;
import android.content.Intent;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
public class Main2Activity extends AppCompatActivity {
ListView listView;

ArrayAdapter<String>adapter;
int [] Images ={R.drawable.chandler,R.drawable.joey,R.drawable.ross,R.drawable.phoebe,R.drawable.monica,R.drawable.rach};
String [] android_version={ "joey","chandler","monica","ross","rachel","phoebe"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
 //       scanwifi=(Button)findViewById(R.id.button4);
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
class customAdadpter extends BaseAdapter{

    @Override
    public int getCount() {
        return 6;


    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}