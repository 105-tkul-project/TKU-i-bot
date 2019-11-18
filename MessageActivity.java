package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    // 使用者發訊息
    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    // chat bot回訊息
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;
    // object initailization
    Button btn_back;
    ImageButton btn_send;
    EditText text_send;
    RecyclerView recyclerView;
    ArrayList<Chat> mchat = new ArrayList<>();
    Intent intent;
    MessageAdapter messageAdapter;
    final Object syncObj = this; // Main thread
    // Socket variable
    private String ip = "163.13.28.46", msg_recv; // see shell>ipconfig
    private int port = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 綁定UI
        btn_back = findViewById(R.id.btn_chat_back);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);
        recyclerView = findViewById(R.id.recycler_view);
        // 設定recyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(this, mchat);
        recyclerView.setAdapter(messageAdapter);

        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        intent = getIntent();
    }

    @Override
    public void onClick(View v) {
        if (v == btn_send) {
            // 存取字串
            String msg = text_send.getText().toString();
            // 驗證訊息是否空白
            if (!msg.equals("")) {
                // 用資料結構打包資料(user sent) update adapter
                mchat.add(new Chat(VIEW_TYPE_MESSAGE_SENT, msg));

                // 新增執行緒(線程) Socket連線
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (Socket s = new Socket(ip, port)) {
                            // setting socket write & read
                            System.out.println("Just connected to " + s.getRemoteSocketAddress());
                            s.setSoTimeout(60 * 1000);
                            // 傳到server
                            DataOutputStream out = new DataOutputStream(s.getOutputStream());
                            // server回傳
                            InputStream dataIn = s.getInputStream();
                            byte[] buffer = new byte[1024];

                            // send data to server
                            out.write(mchat.get(mchat.size() - 1).getMessage().getBytes());
                            out.flush();

                            // receive data
                            msg_recv = new String(buffer, 0, dataIn.read(buffer));
                            // debug_print
                            System.out.print(msg_recv);
                            System.out.flush();

                            // signal main thread
                            synchronized (syncObj) {
                                System.out.println("notify main thread");
                                syncObj.notify();
                            }
                            // close write & read & socket
                            System.out.println("Close InputStream");
                            out.close();
                            dataIn.close();
                            if (!s.isClosed()) {
                                s.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                // block main thread until recieved data to msg_recv
                synchronized (syncObj) {
                    try {
                        syncObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // update adapter
                mchat.add(new Chat(VIEW_TYPE_MESSAGE_RECEIVED, msg_recv));
                // refresh recycleview
                messageAdapter.notifyDataSetChanged();
                // debug (main thread signaled)
                Toast.makeText(this, "send success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MessageActivity.this, "訊息不能空白", Toast.LENGTH_SHORT).show();
            }
            // 訊息傳完後，訊息格清除文字
            text_send.getText().clear();
        } else if (v == btn_back) {
            // 回到首頁
            finish();
        }
    }
}
