package fr.smile.sensortag_ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BLE_scan extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> defaultArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_scan);
        defaultArray = new ArrayList<String>();
        adapter = new <String>ArrayAdapter(this, android.R.layout.simple_list_item_1, defaultArray);
        ListView listView = (ListView) findViewById(R.id.listView);
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
            Toast toast = Toast.makeText(getApplicationContext(), "Yolo", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void scan_le_device(){
        mBluetoothAdapter.startLeScan(mLeScanCallback);

    }
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi,
                                     byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            defaultArray.add(device.getAddress());
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            };

    private void UpdateListView(BluetoothDevice device) {

    }


}
