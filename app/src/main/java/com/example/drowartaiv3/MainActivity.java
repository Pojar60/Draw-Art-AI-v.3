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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager2.widget.ViewPager2;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.drowartaiv3.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    FloatingActionButton floatingActionButton;

//    ActivityMainBinding binding;
    private boolean isSliderVisible = true;


//    GridView gridView;
//    ArrayList<DataClass>  dataList;
//    MyAdapter adapter;
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");


    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

//        floatingActionButton = findViewById(R.id.fab);
//        gridView = findViewById(R.id.gridview);


//        dataList = new ArrayList<>();
//        adapter = new MyAdapter(dataList, this);
//        gridView.setAdapter(adapter);

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    DataClass dataClass = dataSnapshot.getValue(DataClass.class);
//                    dataList.add(dataClass);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });





//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
////                startActivity(intent);
////                finish();
//            }
//        });

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> sliceModels = new ArrayList<>();
        sliceModels.add(new SlideModel(R.drawable._1, ScaleTypes.FIT));
        sliceModels.add(new SlideModel(R.drawable._2, ScaleTypes.FIT));
        sliceModels.add(new SlideModel(R.drawable._3, ScaleTypes.FIT));
        imageSlider.setImageList(sliceModels, ScaleTypes.FIT);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

//        String[] imageName = {"1", "2", "3", "4", "5", "6", "7"," 8"};
//        int[] image = {R.drawable.nature3, R.drawable.nature4, R.drawable.nature5, R.drawable.nature6, R.drawable.nature7, R.drawable.nature9, R.drawable.nature10, R.drawable.nature_2,};



//        binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY > oldScrollY) {
//                    // Прокрутка вниз
//                    hideSliderAndScrollHorizontal();
//                } else if (scrollY < oldScrollY) {
//                    // Прокрутка вверх
//                    showSliderAndScrollHorizontal();
//                }
//            }
//        });
    }

//    private void hideSliderAndScrollHorizontal() {
//        if (isSliderVisible) {
//            binding.imageSlider.setVisibility(View.GONE);
//            binding.nestedScrollView.scrollTo(0, 0); // Скролл в начало NestedScrollView
//            binding.linearLayout.setLayoutParams(new NestedScrollView.LayoutParams(
//                    NestedScrollView.LayoutParams.MATCH_PARENT,
//                    NestedScrollView.LayoutParams.MATCH_PARENT
//            ));
//            isSliderVisible = false;
//        }
//    }

//    private void showSliderAndScrollHorizontal() {
//        if (!isSliderVisible) {
//            binding.imageSlider.setVisibility(View.VISIBLE);
//            binding.linearLayout.setLayoutParams(new NestedScrollView.LayoutParams(
//                    NestedScrollView.LayoutParams.MATCH_PARENT,
//                    NestedScrollView.LayoutParams.WRAP_CONTENT
//            ));
//            isSliderVisible = true;
//        }
//    }


}
