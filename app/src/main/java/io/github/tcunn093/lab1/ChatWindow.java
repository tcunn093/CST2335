package io.github.tcunn093.lab1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    private EditText chatMessageField;
    private Button sendMessageButton;
    private ListView messageListView;
    private ArrayList<String> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        chatMessageField = (EditText) findViewById(R.id.chatMessageField);
        sendMessageButton = (Button) findViewById(R.id.sendChatButton);
        messageListView = (ListView) findViewById(R.id.messageList);

        messageList = new ArrayList<String>();

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        messageListView.setAdapter(messageAdapter);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageList.add(chatMessageField.getText().toString());
                messageAdapter.notifyDataSetChanged();
                chatMessageField.setText("");
            }
        });




    }

    private class ChatAdapter extends ArrayAdapter<String>{

        public ChatAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount(){return messageList.size();};

        public String getItem(int position){return messageList.get(position);};

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position%2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }


    }


}
