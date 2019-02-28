package com.example.thermonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class wifidetect extends AppCompatActivity {
private WifiManager wifiManager;
private ListView listView;
private int size=0;
private List<ScanResult> results;
private ArrayList<String> arrayList =new ArrayList<>();
private ArrayAdapter adapter;
private WifiInfo wifiInfo;
//private customAdadpter customAdadpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifidetect);
        listView=findViewById(R.id.wifidetect);
      //  wifiInfo=wifiManager.getConnectionInfo();
        wifiManager=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!(wifiManager.isWifiEnabled())){
            Toast.makeText(this,"wifi is of..enable it",Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
 adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        scanWifi();
    }
    public void scanWifi(){
        arrayList.clear();
        registerReceiver(wifiReciver,new IntentFilter(wifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
Toast.makeText(this,"scanning....",Toast.LENGTH_LONG).show();
    }
    BroadcastReceiver wifiReciver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results=wifiManager.getScanResults();
            unregisterReceiver(this);
            int i=0;
            for (ScanResult scanResult :results){
                if(((scanResult.SSID).equals("ESPap"))||(scanResult.SSID).equals("ESPap22")){
                arrayList.add(scanResult.SSID);
                i++;
                System.out.print("i is equal to"+i);
                adapter.notifyDataSetChanged();
            }}listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent j=new Intent(wifidetect.this,emptythree.class);
                    startActivity(j);
                }
            });
        }
    };
}
