package com.example.gorent.controle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gorent.R;

public class CarDetail extends AppCompatActivity implements View.OnClickListener {

    public TextView model, year, brand;

    private Button rentCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        setTitle(this.getIntent().getExtras().getString("model"));

        model = (TextView) findViewById(R.id.model);
        year = (TextView) findViewById(R.id.year);
        brand = (TextView) findViewById(R.id.brand);

        model.setText(this.getIntent().getExtras().getString("model"));
        year.setText(this.getIntent().getExtras().getString("year"));
        brand.setText(this.getIntent().getExtras().getString("brand"));

        rentCar = (Button) findViewById(R.id.rent_car);
        rentCar.setOnClickListener(this);
    }

    public void onClick(View v){

        if(v == rentCar){
            startActivity(new Intent(this,ClientList.class));
        }
    }
}
