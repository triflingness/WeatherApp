package com.triflingness.weatherapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class FragTwoActivity extends Fragment {
    public BluetoothSPP bluetoothSPP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bluetoothSPP = new BluetoothSPP(getContext());  
        setBluetooth();
    }

    private void setBluetooth() {
        if (!bluetoothSPP.isBluetoothAvailable()) { //블루투스 사용 불가
            Toast.makeText(getContext()
                    , "Bluetooth is not available"
                    , Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }

        bluetoothSPP.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신
            public void onDataReceived(byte[] data, String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });

        bluetoothSPP.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getContext()
                        , "Connected to " + name + "\n" + address
                        , Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() { //연결해제
                Toast.makeText(getContext()
                        , "Connection lost", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() { //연결실패
                Toast.makeText(getContext()
                        , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
