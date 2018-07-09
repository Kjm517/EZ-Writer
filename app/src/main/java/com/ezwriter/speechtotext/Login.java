package com.ezwriter.speechtotext;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    DatabaseHelper databaseHelper;
    EditText unameLogin, passLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper.addData("test", "12345");

        Button login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomepage(v);
            }
        });
    }

    public void AddData(String username, String password){
        boolean insertData = databaseHelper.addData(username, password);

        if(insertData){
            Toast.makeText(getApplicationContext(), "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Data Insertion FAILED!", Toast.LENGTH_LONG).show();
        }
    }

    public void goToHomepage(View view){
        unameLogin = (EditText)findViewById(R.id.editTextUname);
        passLogin = (EditText)findViewById(R.id.editTextPass);

        if(TextUtils.isEmpty(unameLogin.getText().toString()) == true || TextUtils.isEmpty(passLogin.getText().toString()) == true){
            Intent homepage = new Intent(this, MainActivity.class);
            homepage.putExtra("USERNAME", unameLogin.getText().toString());
            startActivity(homepage);
        }else{
            Toast.makeText(getApplicationContext(), "Invalid Username/Password!", Toast.LENGTH_LONG).show();
        }
    }
}
