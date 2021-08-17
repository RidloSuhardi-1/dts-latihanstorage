package com.example.latihanstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class activity_external extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "storageinfo-EX.txt";
    Button buatFile, ubahFile, bacaFile, hapusFile;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);

        buatFile = (Button) findViewById(R.id.buttonBuatFileEx);
        ubahFile = (Button) findViewById(R.id.buttonUbahFileEx);
        bacaFile = (Button) findViewById(R.id.buttonBacaFileEx);
        hapusFile = (Button) findViewById(R.id.buttonHapusFileEx);
        textBaca = (TextView) findViewById(R.id.textBacaEx);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        hapusFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        jalankanPerintah(v.getId());
    }

    public void jalankanPerintah(int id) {
        switch(id) {
            case R.id.buttonBuatFileEx:
                buatFile();
                break;
            case R.id.buttonUbahFileEx:
                ubahFile();
                break;
            case R.id.buttonBacaFileEx:
                bacaFile();
                break;
            case R.id.buttonHapusFileEx:
                hapusFile();
                break;
        }
    }

    public void buatFile() {
        String isiFile = "Hello World";
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(getApplicationContext(), "Tidak ada media terpasang", Toast.LENGTH_LONG).show();
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(getApplicationContext(), "File berhasil dibuat di penyimpanan eksternal", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ubahFile() {
        String ubahFile = "Coba ubah file dari external storage";
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubahFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(getApplicationContext(), "File berhasil diubah di penyimpanan eksternal", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bacaFile() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, FILENAME);

        if (file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();
                while(line!=null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            textBaca.setText(text.toString());
        }
    }

    public void hapusFile() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, FILENAME);

        if(file.exists()) {
            file.delete();

            Toast.makeText(getApplicationContext(), "File berhasil dihapus dari penyimpanan eksternal", Toast.LENGTH_LONG).show();
        }
    }
}