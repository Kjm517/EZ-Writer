package com.ezwriter.speechtotext;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _regbtn, _regLogin;
    EditText _userReg, _passReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper = new DatabaseHelper(register.this);
        _regbtn = (Button)findViewById(R.id.regbtn);
        _regLogin = (Button)findViewById(R.id.regLogin);
        _userReg = (EditText)findViewById(R.id.userReg);
        _passReg = (EditText)findViewById(R.id.passReg);

        // REGISTER BUTTON
        _regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String uname = _userReg.getText().toString();
                String pass = _passReg.getText().toString();
                insertData(uname, pass);
                Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(register.this, Login.class);
                startActivity(intent);
            }
        });
        // LOGIN BUTTON
        _regLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, Login.class);
                startActivity(intent);

            }
        });
    }
    public void insertData(String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, username);
        contentValues.put(DatabaseHelper.COL_3, password);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}
