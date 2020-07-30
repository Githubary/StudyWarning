package com.example.myapplication.mvp.view.originalFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.utils.ViewPager.ImgScroll;

import java.util.ArrayList;
import java.util.List;

public class indexFragment extends Fragment {


    View view;
    List<View> listViews;
    ImgScroll imgScroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_fragment_index, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imgScroll=view.findViewById(R.id.viewpager);
        InitViewPager();
        imgScroll.start(getActivity(),listViews,4000);
    }

    @Override
    public void onResume() {
        imgScroll.startTimer();
        super.onResume();
    }

    @Override
    public void onStop() {
        imgScroll.stopTimer();
        super.onStop();
    }


    private void InitViewPager() {
        listViews = new ArrayList<>();
        int[] imageGroup = new int[] { R.drawable.lunbo1, R.drawable.lunbo4, R.drawable.lunbo2,R.drawable.lunbo5 };
        for(int i=0;i<imageGroup.length;i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageGroup[i]);
            listViews.add(imageView);
        }

    }
}
