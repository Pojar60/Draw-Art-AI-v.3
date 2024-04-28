package com.example.drowartaiv3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Nature extends Fragment {
    private GridView gridView;
    private ImageAdapterNature imageAdapterNature;
    private ArrayList<DataClass> dataList;
    private int savedScrollPosition = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nature, container, false);
        gridView = view.findViewById(R.id.gridviewNature);
        dataList = new ArrayList<>();
        imageAdapterNature = new ImageAdapterNature(dataList, getContext());
        gridView.setAdapter(imageAdapterNature);
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
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Nature");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);
                }
                imageAdapterNature.notifyDataSetChanged();
                gridView.setSelection(savedScrollPosition); // Восстановление позиции после загрузки данных

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("AnimeFragment", "Failed to read value.", error.toException());
            }
        });
    }
}
