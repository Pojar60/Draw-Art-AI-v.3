package com.example.drowartaiv3;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Cartoons extends Fragment {
    private GridView gridView;
    private ImageAdapterCartoons imageAdapterCartoons;
    private ArrayList<DataClass> dataList;
    private int savedScrollPosition = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartoons, container, false);
        gridView = view.findViewById(R.id.gridviewCartoons);
        dataList = new ArrayList<>();
        imageAdapterCartoons = new ImageAdapterCartoons(dataList, getContext());
        gridView.setAdapter(imageAdapterCartoons);
        loadData();
        if (savedInstanceState != null) {
            savedScrollPosition = savedInstanceState.getInt("scrollPosition", 0);
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        gridView.setSelection(savedScrollPosition); // Восстановление позиции прокрутки
    }

    @Override
    public void onPause() {
        super.onPause();
        savedScrollPosition = gridView.getFirstVisiblePosition(); // Сохранение позиции прокрутки
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scrollPosition", gridView.getFirstVisiblePosition()); // Сохранение позиции в состоянии
    }
    private void loadData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images_3");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);
                }
                imageAdapterCartoons.notifyDataSetChanged();
                gridView.setSelection(savedScrollPosition); // Восстановление позиции после загрузки данных

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("CartoonsFragment", "Failed to read value.", error.toException());
            }
        });
    }
}
