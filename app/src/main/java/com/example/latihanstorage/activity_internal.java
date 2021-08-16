package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class activity_internal extends AppCompatActivity implements View.OnClickListener {

    public static final String FILENAME = "storageinfo.txt";
    Button buatFile, ubahFile, bacaFile, hapusFile;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        buatFile = (Button) findViewById(R.id.buttonBuatFile);
        ubahFile = (Button) findViewById(R.id.buttonUbahFile);
        bacaFile = (Button) findViewById(R.id.buttonBacaFile);
        hapusFile = (Button) findViewById(R.id.buttonHapusFile);
        textBaca = (TextView) findViewById(R.id.textBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        hapusFile.setOnClickListener(this);

    }

    @Override
    public void onClick( View v) {
        jalankanPerintah(v.getId());
    }

    public void jalankanPerintah( int id) {
        switch ( id) {
            case R.id.buttonBuatFile:
                buatFile();
                break;
            case R.id.buttonUbahFile:
                ubahFile();
                break;
            case R.id.buttonBacaFile:
                bacaFile();
                break;
            case R.id.buttonHapusFile:
                hapusFile();
                break;
        }
    }

    void buatFile() {
        String isiFile = "Coba isi data file text";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(getApplicationContext(), "Berkas berhasil dibuat", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ubahFile() {
        String ubah = "Update isi data file text";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(getApplicationContext(), "Berkas berhasil diperbarui", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void bacaFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();
                while( line != null) {
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

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();

            Toast.makeText(getApplicationContext(), "Berkas \'" + FILENAME + "\' berhasil dihapus", Toast.LENGTH_LONG).show();
        }
    }
}