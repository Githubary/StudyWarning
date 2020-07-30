package com.example.myapplication.mvp.view.loginAndregister;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.example.myapplication.mvp.view.originalFragment.DrawerActivity;
import com.example.myapplication.mvp.view.originalFragment.indexFragment;

public class loginchooseActivity extends BaseActivity {
    ImageView image_student;
    ImageView image_teacher;
    RelativeLayout relativeLayout;
    AnimatorSet animatorSet;


    //触摸事件属性定义
    private float mPosX;
    private float mPosY;
    private float mCurrentPosX;
    private float mCurrentPosY;

    //记录登录身份
    private String identity;

    private boolean t=true;
    private boolean s=true;

    //初始化布局
    @Override
    protected int initLayout() {
        return R.layout.activity_loginchoose;
    }

    //初始化控件
    @Override
    protected void initView() {
        image_student=findViewById(R.id.img_student);
        image_teacher=findViewById(R.id.img_teacher);
        relativeLayout=findViewById(R.id.relative_addimg);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //初始化页面信息
        setTitle(true);       //写在onCreate之前
        setState(false);       //写在onCreate之前
        super.onCreate(savedInstanceState);

        /**建立身份管理库*/
        final SharedPreferences.Editor editor = getSharedPreferences("currentUserMessage", Context.MODE_PRIVATE).edit();



        image_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**点击了学生，则将身份置为学生*/
                editor.putString("identity","student");
                editor.apply();

                if(s) {
                    changeBig();
                    s=false;
                    t=true;
                }
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(1000);//休眠3秒
                            loginchooseActivity.this.finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });
        image_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editor.putString("identity","teacher");
                editor.apply();

                if(t) {
                    changeSmall();
                    t=false;
                    s=true;
                }
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(1000);//休眠3秒
                            loginchooseActivity.this.finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });

        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    // 按下
                    case MotionEvent.ACTION_DOWN:
                        mPosX = event.getX();
                        mPosY = event.getY();
                        break;
                    // 移动
                    case MotionEvent.ACTION_MOVE:
                        mCurrentPosX = event.getX();
                        mCurrentPosY = event.getY();

                        if (mCurrentPosX - mPosX > 50 && Math.abs(mCurrentPosY - mPosY) < 20){
                            changeBig();s=false;t=true;}
                        else if(mCurrentPosX - mPosX < -50 && Math.abs(mCurrentPosY - mPosY) < 20){
                            changeSmall();s=true;t=false;}
                        break;
                    // 拿起
                    case MotionEvent.ACTION_UP :
                        break;
                    default:
                        break;
                }
                return true;
            }
        });




    }
    public void changeBig() {
//        这里是直接写在代码里的
//        ObjectAnimator animatorA =ObjectAnimator.ofFloat(image_student,"translationX",0,60,60);
//        ObjectAnimator animatorB =ObjectAnimator.ofFloat(image_student,"scaleY",1f,1.2f,1.2f);
//        ObjectAnimator animatorC =ObjectAnimator.ofFloat(image_teacher,"scale",1f,0f,0f);
//        ObjectAnimator animatorD =ObjectAnimator.ofFloat(image_teacher,"alpha",1.0f,0f,0f);

//        这里是写在xml文件中的
        Animator animatorS = AnimatorInflater.loadAnimator(this,R.animator.loginbig);
        //设置放大中心
        image_student.setPivotX(0);
        image_student.setPivotY(image_student.getHeight()/2);
        image_student.invalidate();
        animatorS.setTarget(image_student);
        Animator animatorT = AnimatorInflater.loadAnimator(this,R.animator.loginsmall);
        image_teacher.setPivotX(image_teacher.getWidth());
        image_teacher.setPivotY(0);
        image_student.invalidate();
        animatorT.setTarget(image_teacher);
        //将animator添加到animator组中，使得一起播放（with，after，before）
        animatorSet= new AnimatorSet();
        animatorSet.play(animatorS).with(animatorT);
        animatorSet.start();


        relativeLayout.removeView(image_student);
        relativeLayout.addView(image_student);
        image_student.setAlpha(1.0f);



//        animatorSet = new AnimatorSet();
//        animatorSet.play(animatorA).with(animatorB).with(animatorC).with(animatorD);
//        animatorSet.setDuration(2000);
//        animatorSet.start();




//        image_teacher.setVisibility(View.GONE);
////        Sanimation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.small);
////        image_teacher.setAnimation(Sanimation);
////        Banimation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.big);
////        image_student.setAnimation(Banimation);
////        animationSet = new AnimationSet(false);
////        animationSet.addAnimation(Sanimation);
////        animationSet.addAnimation(Banimation);
////        animationSet.start();


      //强行设置图片大小，不够好看
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) image_student.getLayoutParams();
//        params.height=270;
//        params.width =300;
//        image_student.setLayoutParams(params);
    }
    public void changeSmall(){
//        Sanimation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.small);
//        image_teacher.setAnimation(Sanimation);
//        Sanimation.start();

        Animator animatorT = AnimatorInflater.loadAnimator(this,R.animator.loginbig);
        image_teacher.setPivotX(image_teacher.getWidth());
        image_teacher.setPivotY(image_teacher.getHeight()/2);
        image_teacher.invalidate();
        animatorT.setTarget(image_teacher);
        Animator animatorS = AnimatorInflater.loadAnimator(this,R.animator.loginsmall);
        image_student.setPivotX(0);
        image_student.setPivotY(image_student.getHeight());
        image_student.invalidate();
        animatorS.setTarget(image_student);



        animatorSet= new AnimatorSet();
        animatorSet.play(animatorS).with(animatorT);
        animatorSet.start();

        relativeLayout.removeView(image_teacher);
        relativeLayout.addView(image_teacher);
        image_teacher.setAlpha(1.0f);




    }

}
