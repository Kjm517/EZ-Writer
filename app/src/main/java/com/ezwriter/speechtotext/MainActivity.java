package com.ezwriter.speechtotext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AccelerometerListener {

	Button nextbtn, logout;
	TextView txvResult;
    //Session session;

    TextHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txvResult = (TextView) findViewById(R.id.txvResult);
		nextbtn = (Button)findViewById(R.id.nextbtn);
        logout = (Button)findViewById(R.id.logoutbtn);

        //SESSION
//        session = new Session(this);
//        if(!session.loggedIn()){
//            logout();
//        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

		nextbtn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, MainWriter.class);
				startActivity(intent);

			}
		});

	}

    public void insertData () {
        String input = txvResult.getText().toString();

        db.addData(input);
    }

    private void logout(){
        //session.setLoggedIn(false);
        finish();
        startActivity(new Intent(MainActivity.this, Login.class));
    }

	public void getSpeechInput(View view) {

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(intent, 10);
		} else {
			Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case 10:
				if (resultCode == RESULT_OK && data != null) {
					ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
					txvResult.setText(result.get(0));
				}
				break;
		}
	};

    @Override
    protected void onResume() {
        super.onResume();
        if (AccelerometerManager.isSupported(this)) {
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        startActivity(new Intent(MainActivity.this, MainWriter.class));
    }

    @Override
    public void onStop() {
        super.onStop();

//Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

//Start Accelerometer Listening
            AccelerometerManager.stopListening();

            Toast.makeText(this, "onStop Accelerometer Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();

            Toast.makeText(this, "onDestroy Accelerometer Stopped", Toast.LENGTH_SHORT).show();
        }
    }


}

