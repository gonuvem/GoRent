package com.example.gorent.controle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gorent.R;
import com.example.gorent.entidade.Car;

public class CarDetail extends AppCompatActivity {

    public TextView model, year, brand;



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

    }
}
