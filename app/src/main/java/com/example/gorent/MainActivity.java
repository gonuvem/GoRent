package com.example.gorent;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.example.gorent.entidade.Client;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    private FloatingActionButton buttonSettings;
    private FloatingActionButton cameraButton;
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;
    public  final ArrayList<Car> list_car = new FireControl().retrive_all_cars();

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
            // Start the SecondActivity
            Intent intent = new Intent(this, QrCodeActivity.class);
            startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
        }
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                // get String data from Intent
                String returnString = data.getStringExtra("carKey");
                final Context context = this;
                for (Car car: list_car) {
                    if (car.key.equals("-" + returnString)) {
                        Intent detailIntent = new Intent(context, CarDetail.class);
                        detailIntent.putExtra("model", car.model);
                        detailIntent.putExtra("brand", car.brand);
                        detailIntent.putExtra("year", car.year);
                        startActivity(detailIntent);
                    }
                }
            }
        }
    }
}