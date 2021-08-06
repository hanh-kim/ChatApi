package com.hanhpk.chatapi.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanhpk.chatapi.R;
import com.hanhpk.chatapi.model.ChatFactory;
import com.hanhpk.chatapi.model.ChatType;
import com.hanhpk.chatapi.model.Result;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatFactory> list;

    public void setData(List<ChatFactory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ChatType.TYPE_MY_MESSAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_my_message, parent, false);
            return new MessageViewHolder(view);
        } else if (viewType == ChatType.TYPE_RESPOND_MESSAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_respond_message, parent, false);
            return new RespondViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ChatFactory factory = list.get(position);
        if (factory == null) return;
        bindHolder(holder, factory);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if (list != null) {
            if (list.get(position).getTypeId() == ChatType.TYPE_MY_MESSAGE) {
                return ChatType.TYPE_MY_MESSAGE;
            } else if (list.get(position).getTypeId() == ChatType.TYPE_RESPOND_MESSAGE) {
                return ChatType.TYPE_RESPOND_MESSAGE;
            }
        }
        return 0;
    }

    private void bindHolder(RecyclerView.ViewHolder holder, ChatFactory factory) {
        if (factory.getTypeId() == ChatType.TYPE_MY_MESSAGE) {
            bindMyMessage(holder, factory);
        } else if (factory.getTypeId() == ChatType.TYPE_RESPOND_MESSAGE) {
            bindRespondMessage(holder, factory);
        }
    }

    private void bindMyMessage(RecyclerView.ViewHolder holder, ChatFactory factory) {
        MessageViewHolder viewHolder = (MessageViewHolder) holder;
        viewHolder.tvMessage.setText(factory.getMessage());
    }

    private void bindRespondMessage(RecyclerView.ViewHolder holder, ChatFactory factory) {
        RespondViewHolder viewHolder = (RespondViewHolder) holder;

        new Handler().postDelayed(()->{
            viewHolder.loading.setVisibility(View.GONE);
            viewHolder.tvMessage.setText(factory.getResult().getChatbot().getResponse());
            viewHolder.tvMessage.setVisibility(View.VISIBLE);
        },2000);


    }
}
