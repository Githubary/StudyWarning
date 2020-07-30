package com.example.myapplication.mvp.view.originalFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.example.myapplication.R;
import com.example.myapplication.base.Bfragment.BaseFragment;
import com.example.myapplication.mvp.view.myMessageFragmentFunction.my_collection_Activity;
import com.example.myapplication.mvp.view.myMessageFragmentFunction.MyMessageActivity;
import com.example.myapplication.mvp.view.myMessageFragmentFunction.my_set_Activity;
import com.example.myapplication.mvp.view.myMessageFragmentFunction.my_wallet_Activity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static android.content.Context.MODE_PRIVATE;


public class PersonalCenterFragment extends BaseFragment implements View.OnClickListener {


    private RelativeLayout relativeLayout;
    private TextView my_name;
    private TextView my_collection;
    private TextView my_resource;
    private TextView my_wallet;
    private TextView my_set;
    private TextView my_nickName;
    private ImageView circleImageView;
    private SharedPreferences pref;
    String username;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_my_message, container, false);
         setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.voidtoolbar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void initView(){
        /**设置昵称*/
        my_nickName = view.findViewById(R.id.my_nickName);
        my_nickName.setText(pref.getString("currentUserNickName",""));
        /**设置用户名*/
        my_name=view.findViewById(R.id.my_name);
        my_name.setText("用户名："+username);
        String url = "http://47.92.142.206:8080/images/"+username+".jpg";
        try {
            username= URLDecoder.decode(username, "UTF-8");
            url = "http://47.92.142.206:8080/images/"+username+".jpg";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("----------------"+url);
        String updateTime = String.valueOf(System.currentTimeMillis());
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.defaultcoursebackground)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .signature(new ObjectKey(String.valueOf(System.currentTimeMillis())))
                .into(circleImageView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pref = getActivity().getSharedPreferences("currentUserMessage",MODE_PRIVATE);
        username = pref.getString("currentUserName","");
        /**初始化控件*/
        relativeLayout = view.findViewById(R.id.my_header);
        my_collection=view.findViewById(R.id.my_collection);
        my_resource=view.findViewById(R.id.my_resource);
        my_wallet=view.findViewById(R.id.my_wallet);
        my_set=view.findViewById(R.id.my_set);
        circleImageView = view.findViewById(R.id.CircleImageView);
        initView();

        /**控件监听事件*/
        relativeLayout.setOnClickListener(this);
        my_collection.setOnClickListener(this);
        my_resource.setOnClickListener(this);
        my_wallet.setOnClickListener(this);
        my_set.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.my_header:
                changeActivity(MyMessageActivity.class);
                break;
            case R.id.my_collection:
                changeActivity(my_collection_Activity.class);
                break;
            case R.id.my_resource:
                break;
            case R.id.my_wallet:
                changeActivity(my_wallet_Activity.class);
                break;
            case R.id.my_set:
                changeActivity(my_set_Activity.class);
                break;
        }
    }

    @Override
    public void onStart() {
        initView();
        super.onStart();
    }

}
