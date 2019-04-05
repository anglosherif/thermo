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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class wifidetect extends AppCompatActivity {
    private WifiManager wifiManager;
    static String macAdress;
    static String macAdressnew;
    static String macAdressnew2;
static  String ssid;
    private ListView listView;
    private int size=0;
    private boolean still=true;
    static String x;
    List<ScanResult> results;
     ArrayList<String> arrayList =new ArrayList<>();
    private ArrayAdapter adapter;
     ArrayList<String> MAC=new ArrayList<>();
     CustomAadapter customAadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifidetect);
        listView=findViewById(R.id.wifiList);
        //  wifiInfo=wifiManager.getConnectionInfo();
         customAadapter=new CustomAadapter();
        wifiManager=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!(wifiManager.isWifiEnabled())){
            Toast.makeText(this,"wifi is of..enable it",Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
        //adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(customAadapter);
        scanWifi();
    }
    public void scanWifi(){

        arrayList.clear();
        registerReceiver(wifiReciver,new IntentFilter(wifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        wifiManager.startScan();
        Toast.makeText(this,"scanning....",Toast.LENGTH_LONG).show();}

    BroadcastReceiver wifiReciver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            results=wifiManager.getScanResults();
            unregisterReceiver(this);

            for (ScanResult scanResult :results){

                if(((scanResult.SSID).equals("ESPap"))||(scanResult.SSID).equals("ESPap22")){
                    arrayList.add(scanResult.SSID);
                    MAC.add(scanResult.BSSID);
                    String x=scanResult.BSSID.toUpperCase();

                    customAadapter.notifyDataSetChanged();}
                }listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    macAdress=MAC.get(position);
                    macAdressnew=macAdress.substring(0,1)+'4'+macAdress.substring(2);
                macAdressnew2=macAdressnew.toUpperCase();
                ssid=arrayList.get(position);
                //   Toast.makeText(wifidetect.this,macAdressnew2,Toast.LENGTH_SHORT).show();
                   // System.out.print(wifidetect.macAdress);
                    still=false;
                    Intent j=new Intent(wifidetect.this,emptythree.class);
                    startActivity(j);
                }
            });
        }
    };
    class CustomAadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size() ;
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
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.customlayout, null);
            ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView2);
            TextView router_names=(TextView)convertView.findViewById((R.id.textView2));
            TextView mac=(TextView)convertView.findViewById((R.id.textView4));
imageView.setImageResource(R.drawable.esplogo);
router_names.setText(arrayList.get(position));
mac.setText(MAC.get(position));
            return convertView;
        }
    }
}

















