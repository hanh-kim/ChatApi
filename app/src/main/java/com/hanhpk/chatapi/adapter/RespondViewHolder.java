package com.hanhpk.chatapi.adapter;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.hanhpk.chatapi.R;

import org.jetbrains.annotations.NotNull;

public class RespondViewHolder extends RecyclerView.ViewHolder {

    public TextView tvMessage;
    public TextView  tvName;
    public ImageView avatar;
    public ProgressBar loading;
    public RespondViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        tvMessage = itemView.findViewById(R.id.tvMessage);
        tvName = itemView.findViewById(R.id.tvName);
        avatar = itemView.findViewById(R.id.imgAvatar);
        loading=itemView.findViewById(R.id.progressBarLoading);
        loading.setIndeterminateDrawable(new ThreeBounce());

    }

}
