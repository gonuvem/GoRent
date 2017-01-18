package com.example.gorent.controle;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gorent.R;
import com.example.gorent.dados.FireControl;
import com.example.gorent.entidade.Client;

import java.util.ArrayList;

public class ClientList extends AppCompatActivity implements View.OnClickListener {


    private ListView mListView;
    public  final ArrayList<Client> list_clients = new FireControl().retrive_all_clients();
    private FloatingActionButton addClientBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        mListView = (ListView) findViewById(R.id.client_list_view);


        setTitle("Clientes");

        ClientAdapter adapter = new ClientAdapter(this, list_clients);
        mListView.setAdapter(adapter);


        final Context context = this;
        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = list_clients.get(position);

                Intent detailIntent = new Intent(context, ClientDetail.class);
                detailIntent.putExtra("name", client.name);
                detailIntent.putExtra("adress", client.adress);
                detailIntent.putExtra("id", client.id);

                startActivity(detailIntent);
            }

        });

        addClientBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addClientBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v == addClientBtn){
            startActivity(new Intent(this,AddClient.class));
        }
    }
}
