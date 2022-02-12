package com.example.mybankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.mybankapp.Adapters.TransferAdapter;
import com.example.mybankapp.DS.BankDb;
import com.example.mybankapp.Models.Transfer;

import java.util.ArrayList;

public class TransfersACT extends AppCompatActivity {
    TransferAdapter transferAdapter;
    ArrayList<Transfer> transfers = new ArrayList<>();
    BankDb db;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);
        db = new BankDb(this);
        setArrayDb();

        recyclerView =findViewById(R.id.recycler);

        transferAdapter = new TransferAdapter(transfers, this);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(transferAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void setArrayDb() {

        transfers.clear();

        Cursor cursor = db.showTransfers();
        //   Log.i("dss",cursor.getString(2)+""+cursor.getString(1));
        while (!cursor.isAfterLast()) {

                transfers.add(new Transfer(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            cursor.moveToNext();
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}