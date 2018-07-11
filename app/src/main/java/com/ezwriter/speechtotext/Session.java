package com.ezwriter.speechtotext;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context){
        this.context = context;
        prefs = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedIn(boolean loggedin){
        editor.putBoolean("loggedInmode", loggedin);
        editor.commit();
    }

    public boolean loggedIn(){
        return prefs.getBoolean("loggedInmode", false);
    }
}
