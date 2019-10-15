package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

class MessageAdapter extends RecyclerView.Adapter {

    // 使用者發訊息
    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    // chatbot回訊息
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;

    private ArrayList<Chat> mChat = new ArrayList<>();

    public MessageAdapter(Context context, ArrayList<Chat> mChat) {
        this.mChat = mChat;
    }


    @NonNull
    @Override // 更新View
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 創造新的view，分開使用者端跟BOT端的view
        View view;
        // 訊息來自使用者端
        if(viewType == VIEW_TYPE_MESSAGE_SENT){
            // 更新UI
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right,parent,false);
            // 更新使用者的view(訊息出現在右邊)
            return new Sender(view);
        // 訊息來自BOT端
        }else if(viewType == VIEW_TYPE_MESSAGE_RECEIVED){
            // 更新UI
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left,parent,false);
            // 更新BOT的view(訊息出現在左邊)
            return new Receiver(view);
        }else{
            // 例外錯誤
            Toast.makeText(parent.getContext(),"MainAdapter error",Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override // 取得特定位置的資料並連結UI
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 分辨誰傳的資料
        if(holder instanceof Sender){
            // 取得字串
            ((Sender) holder).getShow_message().setText(mChat.get(position).getMessage());
        }else if(holder instanceof Receiver){
            ((Receiver) holder).getItemview().setText(mChat.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    // 綁定使者端的layout.chat_item_right的UI
    private class Sender extends RecyclerView.ViewHolder{
        private TextView show_message;
        Sender(View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
        }

        TextView getShow_message(){
            return show_message;
        }
    }
    // 綁定BOT端的layout.chat_item_left的UI
    private class Receiver extends RecyclerView.ViewHolder{
        private ImageView mItemImage;
        private TextView mItemview;
        Receiver(View itemView) {
            super(itemView);
            mItemImage = (ImageView) itemView.findViewById(R.id.imageView);
            mItemview = (TextView) itemView.findViewById(R.id.textView);
        }
        public ImageView getItemImage(){
            return mItemImage;
        }

        TextView getItemview(){
            return mItemview;
        }
    }


}
