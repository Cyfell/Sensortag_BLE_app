package fr.smile.sensortag_ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BLE_scan extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> MaccaddrArray;
    private ArrayList<BluetoothDevice> DevicesFounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_scan);

        MaccaddrArray = new ArrayList<String>();
        DevicesFounds = new ArrayList<BluetoothDevice>();
        adapter = new <String>ArrayAdapter(this, android.R.layout.simple_list_item_1, MaccaddrArray);

        final ListView listView = (ListView) findViewById(R.id.listView);
        final Intent intent = new Intent(this, GATT_scanner.class);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView parent, View v, int position, long id){
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
                intent.putExtra("btdevice",DevicesFounds.get(position));
                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);

        init_Bluetooth_Adapter();

        scan_le_device();


    }

    private void init_Bluetooth_Adapter(){
        // Initializes Bluetooth adapter.
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // Ensures Bluetooth is available on the device and it is enabled. If not,
        // displays a dialog requesting user permission to enable Bluetooth.
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Bluetooth not enabled", Toast.LENGTH_SHORT);
            toast.show();
        } else{
            Toast toast = Toast.makeText(getApplicationContext(), "Bluetooth enabled", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void scan_le_device(){
        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }
    //private LeDeviceListAdapter mDeviceListAdapter;
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi,
                                     byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!MaccaddrArray.contains(device.getAddress())){
                                // Add newly detected device in the list
                                MaccaddrArray.add(device.toString());
                                adapter.notifyDataSetChanged();
                                DevicesFounds.add(device);
                            }

                        }
                    });
                }
            };


}
