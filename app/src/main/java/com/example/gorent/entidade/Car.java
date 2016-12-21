package com.example.gorent.entidade;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by orlandoamorim on 15/11/16.
 */

@IgnoreExtraProperties
public class Car {
    public String model;
    public String brand;
    public String year;
    public String key;

    public Car(){
        // Default constructor required for calls to DataSnapshot.getValue(Carro.class);
    }

    public Car(String model, String brand, String year){
        this.model = model;
        this.brand = brand;
        this.year = year;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("model", model);
        result.put("brand", brand);
        result.put("year", year);

        return result;
    }
}
