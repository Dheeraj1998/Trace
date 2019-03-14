package com.example.trace;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;
    String final_data = "";

    String[] family_members = {"Abram", "Advik", "Akarsh", "Anay"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_home);

        Toast.makeText(HomeActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();

        FloatingActionButton add_button = findViewById(R.id.add_btn);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final_data = "";
                    findBT();
                    openBT();
                } catch (IOException ex) {
                }
            }
        });

        FloatingActionButton stop_button = findViewById(R.id.stop_btn);
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    closeBT();
                } catch (IOException ex) {
                }
            }
        });
    }

    void findBT() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "No bluetooth adapter available", Toast.LENGTH_SHORT).show();
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getName().equals("HC-05")) {
                    mmDevice = device;
                    break;
                }
            }
        }
        Toast.makeText(getApplicationContext(), "Bluetooth Device Found", Toast.LENGTH_SHORT).show();
    }

    void openBT() throws IOException {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID
        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        mmSocket.connect();
        mmOutputStream = mmSocket.getOutputStream();
        mmInputStream = mmSocket.getInputStream();

        beginListenForData();

        Toast.makeText(getApplicationContext(), "Bluetooth Opened", Toast.LENGTH_SHORT).show();
    }

    void beginListenForData() {
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                    try {
                        int bytesAvailable = mmInputStream.available();
                        if (bytesAvailable > 0) {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for (int i = 0; i < bytesAvailable; i++) {
                                byte b = packetBytes[i];
                                if (b == delimiter) {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable() {
                                        public void run() {
                                            final_data = data;
                                        }
                                    });
                                } else {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    } catch (IOException ex) {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }

    void closeBT() throws IOException {
        stopWorker = true;
        mmOutputStream.close();
        mmInputStream.close();
        mmSocket.close();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a corresponding family member:");
        builder.setItems(family_members, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseApp.initializeApp(getApplicationContext());
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                Long tsLong = System.currentTimeMillis() / 1000;
                String datastamp = tsLong.toString();
                final_data = final_data.trim();
                String[] sub_data = final_data.split("a");

                DatabaseReference myRef = database.getReference(family_members[which]).child(datastamp);
                myRef.child("Gas").setValue(sub_data[0]);
                myRef.child("Humidity").setValue(sub_data[1]);
                myRef.child("Temperature").setValue(sub_data[2]);
                myRef.child("Stress").setValue(sub_data[3]);
            }
        });
        builder.show();
    }

    public void startHistory(View view) {
        Intent temp = new Intent(HomeActivity.this, HistoryActivity.class);
        startActivity(temp);
    }

    public void startAnalysis(View view) {
        Intent temp = new Intent(HomeActivity.this, AnalysisActivity.class);
        startActivity(temp);
    }

    public void startFamily(View view) {
        Intent temp = new Intent(HomeActivity.this, FamilyActivity.class);
        startActivity(temp);
    }
}
