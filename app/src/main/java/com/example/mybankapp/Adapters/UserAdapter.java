package com.example.mybankapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapp.Profile;
import com.example.mybankapp.R;
import com.example.mybankapp.RecyclerSelectEvent;
import com.example.mybankapp.Models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    List<User> users;
    Context context;
    RecyclerSelectEvent recyclerSelectEvent;
    public UserAdapter(List<User> users , Context context) {
        this.users = users;
        this.context = context;
    }
    public UserAdapter(List<User> users , Context context,RecyclerSelectEvent recyclerSelectEvent) {
        this.users = users;
        this.context = context;
        this.recyclerSelectEvent =recyclerSelectEvent;
    }

    @NonNull
    @Override


    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        User user = users.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.amount.setText("Amount :"+user.getBalance()+" $");


    }

    @Override
    public int getItemCount() {
        return users.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,amount;
        CardView cardView;
        LinearLayout linearLayout;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cons_item);
            cardView = itemView.findViewById(R.id.card_item);
            linearLayout = itemView.findViewById(R.id.linear_item);
            name = itemView.findViewById(R.id.title_of_item);
            email = itemView.findViewById(R.id.body_of_item);
            amount = itemView.findViewById(R.id.amount_of_item);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerSelectEvent==null){
                        User user =users.get(getAdapterPosition());
                    Intent intent = new Intent(context, Profile.class);
                    intent.putExtra("from",user);
                    context.startActivity(intent);}
                    else {
                        User user =users.get(getAdapterPosition());
                        recyclerSelectEvent.onRecyclerSelectViewClick(user);
                    }
                }
            });

        }
    }
}
