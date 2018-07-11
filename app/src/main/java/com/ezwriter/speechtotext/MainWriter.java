package com.ezwriter.speechtotext;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.os.Environment;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ezwriter.speechtotext.Camera.CameraDetails;

public class MainWriter extends AppCompatActivity {

    ImageView _nextbtn;
    EditText inputText;
    EditText Ftitle;
    TextView response;
    Button saveButton,readButton;
    TextHelper db;

    private String filename = "MySampleText.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_writer);

        response = (TextView) findViewById(R.id.response);
        inputText = (EditText) findViewById(R.id.myInputText);
        Ftitle = (EditText) findViewById(R.id.filetitle);

        //filename = Ftitle.getText().toString()+".pdf";

        _nextbtn = (ImageView)findViewById(R.id.camerabtn);
        _nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainWriter.this, CameraDetails.class);
                startActivity(intent);
            }
        });

        saveButton = (Button) findViewById(R.id.saveExternalStorage);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                    fos.write(inputText.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputText.setText("");
                Toast.makeText(getApplicationContext(), "Saved to External Storage", Toast.LENGTH_LONG).show();
            }
        });

        readButton = (Button) findViewById(R.id.getExternalStorage);
        readButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = new FileInputStream(myExternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine;
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputText.setText(myData);
                Toast.makeText(getApplicationContext(), "Data retrieved from Internal Storage", Toast.LENGTH_LONG).show();

            }
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            saveButton.setEnabled(false);
        }
        else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }


    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
