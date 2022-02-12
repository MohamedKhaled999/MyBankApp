package com.example.mybankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.example.mybankapp.Adapters.UserAdapter;
import com.example.mybankapp.DS.BankDb;
import com.example.mybankapp.Models.Transfer;
import com.example.mybankapp.Models.User;

import java.util.ArrayList;

public class UserActivity2 extends AppCompatActivity implements RecyclerSelectEvent {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ArrayList<User> users = new ArrayList<User>();
    BankDb db;
    User userFrom;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        db = new BankDb(this);
        amount = getIntent().getStringExtra("amount");
        userFrom = (User) getIntent().getSerializableExtra("from");

        setArrayDb();

        userAdapter = new UserAdapter(users, this, this);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public void onRecyclerSelectViewClick(User user) {

        double x =Double.valueOf(userFrom.getBalance())- Double.valueOf(amount);
        double y =Double.valueOf(user.getBalance())+ Double.valueOf(amount);



        Log.i("dcsdm", "onRecyclerSelectViewClick: from" + userFrom.getName() + " to " + user.getName() + "\n" + amount+"ddd"+x);
        db.updateUser(userFrom.getEmail(),x+"");
        db.updateUser(user.getEmail(),y+"");

        db.createTransfer(new Transfer(userFrom.getName(), user.getName(),amount));
        Intent intent =new Intent(this, TransfersACT.class);
        startActivity(intent);
        finish();

    }

    public void setArrayDb() {

        users.clear();

        Cursor cursor = db.showUsers();
        //   Log.i("dss",cursor.getString(2)+""+cursor.getString(1));
        while (!cursor.isAfterLast()) {
            if (!userFrom.getEmail().equals(cursor.getString(1)))
            users.add(new User(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}