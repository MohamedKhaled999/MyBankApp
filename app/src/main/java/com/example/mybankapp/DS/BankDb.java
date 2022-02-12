package com.example.mybankapp.DS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mybankapp.Models.Transfer;
import com.example.mybankapp.Models.User;

public class BankDb extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;

    public BankDb(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Constants.UsersTable.TABLE_NAME
                + "("
                + Constants.UsersTable.USER_NAME + " text not null, "
                + Constants.UsersTable.EMAIL + " text primary key,"
                + Constants.UsersTable.AMOUNT + " text )"
        );


        // create transfers table
        sqLiteDatabase.execSQL("create table " + Constants.TransferTable.TABLE_NAME
                + "(" + Constants.TransferTable.ID + " integer primary key, "
                + Constants.TransferTable.FROM + " text,"
                + Constants.TransferTable.TO + " text,"
                + Constants.TransferTable.AMOUNT + " text"
                + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + Constants.UsersTable.TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + Constants.TransferTable.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void createUser(User user) {
        ContentValues row = new ContentValues();
        row.put(Constants.UsersTable.USER_NAME, user.getName());
        // row.put(com.example.notepad.Constants.CustomersTable.CUSTOMER_ID, customer.getId());
        row.put(Constants.UsersTable.EMAIL, user.getEmail());
        row.put(Constants.UsersTable.AMOUNT, user.getBalance());


        //  row.put(com.example.notepad.Constants.CustomersTable.BIRTH_DATE, customer.getBirthdate());

        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(Constants.UsersTable.TABLE_NAME, null, row);
        sqLiteDatabase.close();

    }
    public Cursor showUsers() {
        sqLiteDatabase = getReadableDatabase();
        String[] rowDetails = {Constants.UsersTable.USER_NAME,Constants.UsersTable.EMAIL,Constants.UsersTable.AMOUNT};
        Cursor curs = sqLiteDatabase.query(Constants.UsersTable.TABLE_NAME, rowDetails, null, null, null, null, null);
        if (curs != null)
            curs.moveToFirst();
        sqLiteDatabase.close();
        return curs;
    }
    public void createTransfer(Transfer transfer) {
        ContentValues row = new ContentValues();
        row.put(Constants.TransferTable.FROM, transfer.getFrom());
        // row.put(com.example.notepad.Constants.CustomersTable.CUSTOMER_ID, customer.getId());
        row.put(Constants.TransferTable.TO, transfer.getTo());
        row.put(Constants.TransferTable.AMOUNT, transfer.getAmount());


        //  row.put(com.example.notepad.Constants.CustomersTable.BIRTH_DATE, customer.getBirthdate());

        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(Constants.TransferTable.TABLE_NAME, null, row);
        sqLiteDatabase.close();

    }
    public Cursor showTransfers() {
        sqLiteDatabase = getReadableDatabase();
        String[] rowDetails = {Constants.TransferTable.ID,Constants.TransferTable.FROM,Constants.TransferTable.TO, Constants.TransferTable.AMOUNT};
        Cursor curs = sqLiteDatabase.query(Constants.TransferTable.TABLE_NAME, rowDetails, null, null, null, null, null);
        if (curs != null)
            curs.moveToFirst();
        sqLiteDatabase.close();
        return curs;
    }
    public void updateUser(String email, String amount) {
        ContentValues row = new ContentValues();
        row.put(Constants.UsersTable.AMOUNT, amount);
//        row.put(Constants.ContactTable.PHONE, contact.getPhone());
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(Constants.UsersTable.TABLE_NAME, row, Constants.UsersTable.EMAIL + "='" + email + "'", null);
        sqLiteDatabase.close();
    }
}
