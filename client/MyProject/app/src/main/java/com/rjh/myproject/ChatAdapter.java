package com.rjh.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    ArrayList<ChatDTO> data;

    public ChatAdapter(ArrayList<ChatDTO> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lif=LayoutInflater.from(parent.getContext());
        View view=lif.inflate(R.layout.chatresource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chat_iv_image.setImageResource(data.get(position).getImgId());
        holder.chat_tv_name.setText(data.get(position).getName());
        holder.chat_tv_body.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView chat_iv_image;
        TextView chat_tv_name,chat_tv_body;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);
            this.chat_iv_image=itemview.findViewById(R.id.chat_iv_img);
            this.chat_tv_name=itemview.findViewById(R.id.chat_tv_name);
            this.chat_tv_body=itemview.findViewById(R.id.chat_tv_body);
        }
    }
}
