package com.example.mybankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mybankapp.DS.BankDb;
import com.example.mybankapp.Models.User;

public class SplashScreen extends AppCompatActivity {
    BankDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        db = new BankDb(this);

        fillData();
        getSupportActionBar().hide();
        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    public void fillData() {
        db.createUser(new User("Mohamed Khaled","mohamedmk@email.com","2000"));
        db.createUser(new User("Ahmed","ahmed@email.com","1535"));
        db.createUser(new User("Youssef ","youssef@email.com","2313"));
        db.createUser(new User(" Khaled","Khaled@email.com","5421"));
        db.createUser(new User(" Abdallah ","abdallah@email.com","1220"));
        db.createUser(new User(" Sayid","sayid@email.com","2007"));
        db.createUser(new User("Abdulrahmman","abdulrahmman@email.com","88"));
        db.createUser(new User("Reem","reem@email.com","5000"));
        db.createUser(new User("Alaa","alaa@email.com","100"));
        db.createUser(new User("Jasmin","jasmin@email.com","3000"));
    }
}