package com.example.myapplication.mvp.view.teacherClassViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.utils.alertUtils.BottomDialog;


public class FileUpFragment extends BaseFragment {

    /**构造方法：创建新实例*/
    private static FileUpFragment instance=null;
    public static FileUpFragment newInstance(){
        if(instance==null){
            instance= new FileUpFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fileup, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.fileuptoolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fileUp:
                new BottomDialog(getActivity())
                        .builder()
                        .setTitle("选择上传方式")
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("云盘", BottomDialog.SheetItemColor.Blue
                                , new BottomDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                        Toast.makeText(getActivity(), "选择了云盘", Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .addSheetItem("图片", BottomDialog.SheetItemColor.Blue
                                , new BottomDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                        Toast.makeText(getActivity(), "选择了图片", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
