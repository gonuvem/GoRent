package com.example.gorent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.gorent.controle.CarAdapter;
import com.example.gorent.controle.CarDetail;
import com.example.gorent.controle.LogOutActivity;
import com.example.gorent.controle.LoginActivity;
import com.example.gorent.dados.FireControl;
import com.example.gorent.controle.QrCodeActivity;
import com.example.gorent.entidade.Car;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    private FloatingActionButton buttonSettings;
    private FloatingActionButton cameraButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.car_list_view);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
        System.out.println(firebaseAuth.getCurrentUser());
        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() == null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }


        setTitle("GoRent");

        final ArrayList<Car> list_car = new FireControl().retrive_all_cars();


        CarAdapter adapter = new CarAdapter(this, list_car);
        mListView.setAdapter(adapter);


        final Context context = this;
        // Set what happens when a list view item is clicked
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = list_car.get(position);

                Intent detailIntent = new Intent(context, CarDetail.class);
                detailIntent.putExtra("model", car.model);
                detailIntent.putExtra("brand", car.brand);
                detailIntent.putExtra("year", car.year);

                startActivity(detailIntent);
            }

        });

        buttonSettings = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        buttonSettings.setOnClickListener(this);
        cameraButton =  (FloatingActionButton) findViewById(R.id.camera);
        cameraButton.setOnClickListener(this);
    }

    public void onClick(View v){

        if(v == buttonSettings){
            startActivity(new Intent(this,LogOutActivity.class));
        }else if (v == cameraButton){
            startActivity(new Intent(this,QrCodeActivity.class));
        }
    }
}