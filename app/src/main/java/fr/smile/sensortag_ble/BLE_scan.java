package fr.smile.sensortag_ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BLE_scan extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble_scan);

        init_Bluetooth_Adapter();


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


}
