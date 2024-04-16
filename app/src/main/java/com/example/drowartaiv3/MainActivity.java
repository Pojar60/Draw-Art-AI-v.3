package com.example.drowartaiv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.drowartaiv3.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;

    ActivityMainBinding binding;
    private boolean isSliderVisible = true;


    GridView gridView;
    ArrayList<DataClass>  dataList;
    MyAdapter adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        floatingActionButton = findViewById(R.id.fab);
        gridView = findViewById(R.id.gridview);


        dataList = new ArrayList<>();
        adapter = new MyAdapter(dataList, this);
        gridView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> sliceModels = new ArrayList<>();
        sliceModels.add(new SlideModel(R.drawable._1, ScaleTypes.FIT));
        sliceModels.add(new SlideModel(R.drawable._2, ScaleTypes.FIT));
        sliceModels.add(new SlideModel(R.drawable._3, ScaleTypes.FIT));
        imageSlider.setImageList(sliceModels, ScaleTypes.FIT);



        String[] imageName = {"1", "2", "3", "4", "5", "6", "7"," 8"};
        int[] image = {R.drawable.nature3, R.drawable.nature4, R.drawable.nature5, R.drawable.nature6, R.drawable.nature7, R.drawable.nature9, R.drawable.nature10, R.drawable.nature_2,};



        binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // Прокрутка вниз
                    hideSliderAndScrollHorizontal();
                } else if (scrollY < oldScrollY) {
                    // Прокрутка вверх
                    showSliderAndScrollHorizontal();
                }
            }
        });
    }

    private void hideSliderAndScrollHorizontal() {
        if (isSliderVisible) {
            binding.imageSlider.setVisibility(View.GONE);
            binding.nestedScrollView.scrollTo(0, 0); // Скролл в начало NestedScrollView
            binding.linearLayout.setLayoutParams(new NestedScrollView.LayoutParams(
                    NestedScrollView.LayoutParams.MATCH_PARENT,
                    NestedScrollView.LayoutParams.MATCH_PARENT
            ));
            isSliderVisible = false;
        }
    }

    private void showSliderAndScrollHorizontal() {
        if (!isSliderVisible) {
            binding.imageSlider.setVisibility(View.VISIBLE);
            binding.linearLayout.setLayoutParams(new NestedScrollView.LayoutParams(
                    NestedScrollView.LayoutParams.MATCH_PARENT,
                    NestedScrollView.LayoutParams.WRAP_CONTENT
            ));
            isSliderVisible = true;
        }
    }


}
