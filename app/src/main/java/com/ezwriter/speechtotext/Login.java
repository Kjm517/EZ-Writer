package com.ezwriter.speechtotext;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText unameLogin, passLogin;
    Button _login, _register;
    Cursor cursor;
    ImageView priv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        priv = (ImageView)findViewById(R.id.privacy3);

        _login = (Button)findViewById(R.id.btnLogin);
        _register = (Button)findViewById(R.id.btnRegister);
        unameLogin = (EditText)findViewById(R.id.editTextUname);
        passLogin = (EditText)findViewById(R.id.editTextPass);
        openHelper = new DatabaseHelper(Login.this);
        db = openHelper.getReadableDatabase();
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String username = unameLogin.getText().toString();
                String password = passLogin.getText().toString();
                cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_2 + "=? AND " + DatabaseHelper.COL_3 + "=?", new String[]{username, password});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent homepage = new Intent(Login.this, MainActivity.class);
                        startActivity(homepage);
                    } else {
                        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        _register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent homepage = new Intent(Login.this, register.class);
                startActivity(homepage);
            }
        });
        priv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, Privacy.class);
//                startActivity(intent);
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/1VP22cIy3DMlDZZL1pWpwwvewscNXX1GeBjgyAAGNFew/edit?usp=sharing"));
                startActivity(i);
            }
        });
    }
}
