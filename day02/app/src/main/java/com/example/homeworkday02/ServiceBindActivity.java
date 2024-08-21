package com.example.homeworkday02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ServiceBindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_bind);
        Button bindBtn = findViewById(R.id.btn_bind);
        Button unbindBtn = findViewById(R.id.btn_unbind);
        Button getBtn = findViewById(R.id.btn_get);

        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceBindActivity.this, BindService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConnection);
                mBound=false;
            }
        });

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBound) {
                    return;
                }
                String bindText = mBindService.getBindText();
                Toast.makeText(ServiceBindActivity.this, bindText, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_return2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private static final String TAG = "ServiceBindActivity";
    private BindService mBindService;
    private boolean mBound;
    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.LocalBinder localBinder = (BindService.LocalBinder) service;
            mBindService = localBinder.getService();
            mBound = true;
            Log.d(TAG, "onServiceConnected()");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.d(TAG, "onServiceDisconnected()");
        }
    };
}