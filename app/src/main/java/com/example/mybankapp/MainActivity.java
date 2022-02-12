package com.example.mybankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.mybankapp.Adapters.UserAdapter;
import com.example.mybankapp.DS.BankDb;
import com.example.mybankapp.Models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
     ArrayList<User> users = new ArrayList<User>();
    UserAdapter userAdapter;
    BankDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new BankDb(this);

        setArrayDb();

        userAdapter = new UserAdapter(users, this);


        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onStart() {
        if(userAdapter !=null)
         setArrayDb() ;
        userAdapter.notifyDataSetChanged();

        super.onStart();
    }

    public void setArrayDb() {

        users.clear();

        Cursor cursor = db.showUsers();
        //   Log.i("dss",cursor.getString(2)+""+cursor.getString(1));
        while (!cursor.isAfterLast()) {
            users.add(new User(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }





    }

}