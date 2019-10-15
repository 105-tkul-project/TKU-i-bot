package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    // 使用者發訊息
    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    // chat bot回訊息
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;

    ImageButton btn_send;
    EditText text_send;
    RecyclerView recyclerView;
    ArrayList<Chat> mchat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 綁定UI
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);
        recyclerView = findViewById(R.id.recycler_view);
        // 設定recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MessageAdapter(this,mchat));

        // intent=getIntent();
        // String message = intent.getStringExtra("id");

        btn_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 存取字串
                String msg = text_send.getText().toString();
                // 驗證訊息是否空白
                if(!msg.equals("")){
                    // 用資料結構打包資料(user sent)
                    mchat.add(new Chat(VIEW_TYPE_MESSAGE_SENT,msg));
                    // 傳到後端連線，傳mChat物件到後端
                    sendMessage(msg);
                }else{
                    Toast.makeText(MessageActivity.this,"訊息不能空白",Toast.LENGTH_SHORT).show();
                }
                // 訊息傳完後，訊息格要清除文字
                text_send.setText("");
            }
        });
    }

    // 連線到後端
    private void sendMessage(String message){
        // Socket 寫在這下面(chat room->python)

    }

    // 從後端回傳
    private void readMessage(String myid,String userid){
        // 靠左的訊息
        mchat = new ArrayList<>();
        // Socket 寫在這下面(python-> chat room)


    }
}
