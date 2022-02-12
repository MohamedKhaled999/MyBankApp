package com.example.mybankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybankapp.Models.User;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    Dialog dialog;
    Button button;
    TextView name,email, amount;
User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button =findViewById(R.id.transfer_bt);
        name =findViewById(R.id.name);
        email =findViewById(R.id.email);
        amount =findViewById(R.id.amount);

        dialog = new Dialog(this);
        button.setOnClickListener(this);
      user  = (User) getIntent().getSerializableExtra("from");
      name.setText(user.getName());
      email.setText(user.getEmail());
      amount.setText("Amount :"+user.getBalance()+" $");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }

    @Override
    public void onClick(View view) {
        showDialog();

    }

    public void showDialog() {
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button dialogConfirm = dialog.findViewById(R.id.confirm_button);
        EditText amount = dialog.findViewById(R.id.amount_ed);


        dialogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNumeric(amount.getText().toString())){
                    if (Double.valueOf(amount.getText().toString()) > Double.valueOf(user.getBalance())) {
                        Toast.makeText(getApplicationContext(), "Please Enter Available Amount", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(getApplicationContext(), UserActivity2.class);
                        intent.putExtra("from", user);
                        intent.putExtra("amount", amount.getText().toString());
                        startActivity(intent);
                        finish();
                    }

            }else
                    Toast.makeText(getApplicationContext(), "Please Enter Available Amount", Toast.LENGTH_SHORT).show();

            }

        });
        dialog.show();
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}