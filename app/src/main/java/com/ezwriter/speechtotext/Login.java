package com.ezwriter.speechtotext;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnLogin, _btnRegister;
    EditText _userLogin, _passwordLogin;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        _btnLogin = (Button)findViewById(R.id.btnlogin);
        _btnRegister = (Button)findViewById(R.id.regbtn);
        _userLogin = (EditText)findViewById(R.id.userLogin);
        _passwordLogin = (EditText)findViewById(R.id.passwordLogin);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _userLogin.getText().toString();
                String password = _passwordLogin.getText().toString();
                Intent intent = new Intent(Login.this, MainActivity.class);
                String query = "SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE "
                        + DatabaseHelper.COL_2 + "=? AND " + DatabaseHelper.COL_3 + "=?";
                cursor = db.rawQuery(query, new String[]{username, password});
                if(cursor != null){
                    if(cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                    }else if(username.equals("") || password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Incorrect username/password. Try again!", Toast.LENGTH_LONG).show();
                    }
                }
                startActivity(intent);
            }
        });
       _btnRegister.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Login.this, register.class);
               startActivity(intent);
           }
       });
    }
}
