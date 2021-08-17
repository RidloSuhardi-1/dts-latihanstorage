package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGotoInternal;
    Button btnBtnGotoExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGotoInternal = (Button) findViewById(R.id.btnInternal);
        btnBtnGotoExternal = (Button) findViewById(R.id.btnExternal);

        btnGotoInternal.setOnClickListener(this);
        btnBtnGotoExternal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInternal:
                gotoInternal(v);
                break;
            case R.id.btnExternal:
                gotoExternal(v);
                break;
        }
    }

    public void gotoInternal(View v) {
        Intent intent = new Intent(MainActivity.this, activity_internal.class);
        startActivity(intent);
    }

    public void gotoExternal(View v) {
        Intent intent = new Intent(MainActivity.this, activity_external.class);
        startActivity(intent);
    }
}