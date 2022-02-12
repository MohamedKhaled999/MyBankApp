package com.example.mybankapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybankapp.R;
import com.example.mybankapp.Models.Transfer;

import java.util.List;
public class TransferAdapter extends  RecyclerView.Adapter<TransferAdapter.MyViewHolder>{
    List<Transfer> transfers;
    Context context;
    public TransferAdapter(List<Transfer> transfers, Context context) {
        this.transfers = transfers;
        this.context = context;
    }

    @NonNull
    @Override
    public TransferAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_item,parent,false);

        return new TransferAdapter.MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull TransferAdapter.MyViewHolder holder, int position) {
        Transfer transfer = transfers.get(position);
        holder.from.setText(transfer.getFrom());
        holder.to.setText(transfer.getTo());
        holder.amount.setText("Amount :"+transfer.getAmount()+" $");
    }

    @Override
    public int getItemCount() {
        return transfers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView from,to,amount;
        CardView cardView;
        LinearLayout linearLayout;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cons_item);
            cardView = itemView.findViewById(R.id.card_item);
            linearLayout = itemView.findViewById(R.id.linear_item);
            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            amount = itemView.findViewById(R.id.amount_item);
        }
    }
}
