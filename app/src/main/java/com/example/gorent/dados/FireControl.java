package com.example.gorent.dados;

import android.util.Log;

import com.example.gorent.entidade.Car;
import com.example.gorent.entidade.Client;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by orlandoamorim on 15/11/16.
 */

public class FireControl {

    public FireControl() {}

    private static final String TAG = "FireControl";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myCarRef = database.getReference("car");
    DatabaseReference myClientRef = database.getReference("client");

    public void writeNewCar(Car car){
        String key = myCarRef.push().getKey();
        Map<String, Object> carroValues = car.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, carroValues);
        myCarRef.updateChildren(childUpdates);
    }

    public ArrayList<Car> retrive_all_cars() {
        final ArrayList<Car> list_car = new ArrayList<Car>();
        myCarRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot carroSnap : dataSnapshot.getChildren()) {
                    Car car = carroSnap.getValue(Car.class);
                    car.key = carroSnap.getKey();
                    list_car.add(car);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });

        return list_car;
    }

    public void writeNewClient(Client client){
        String key = myClientRef.push().getKey();
        Map<String, Object> clientValues = client.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, clientValues);
        myClientRef.updateChildren(childUpdates);
    }

    public ArrayList<Client> retrive_all_clients() {
        final ArrayList<Client> list_client = new ArrayList<Client>();
        myClientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot carroSnap : dataSnapshot.getChildren()) {
                    Client client = carroSnap.getValue(Client.class);
                    client.key = carroSnap.getKey();
                    list_client.add(client);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });

        return list_client;
    }
}
