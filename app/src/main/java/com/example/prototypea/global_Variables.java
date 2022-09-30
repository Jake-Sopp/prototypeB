package com.example.prototypea;

import android.app.Application;

public class global_Variables extends Application {
    private String user;
    public String return_user(){
        return user;
    }
    public void Set_user(String value){
        user=value;
    }
}
