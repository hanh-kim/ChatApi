package com.hanhpk.chatapi.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanhpk.chatapi.R;

import org.jetbrains.annotations.NotNull;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView tvMessage;
    public MessageViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        tvMessage = itemView.findViewById(R.id.tvMyMessage);
    }
}
