package com.example.myapplication.mvp.viewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.R;
import com.example.myapplication.entity.EnterCourse;

import java.util.List;

public class EnterCourseItemAdapter extends ArrayAdapter<EnterCourse> {

    /**courseItem布局的地方*/
    private int itemLayout;

    View view;

    String url ="http://47.92.142.206:8080/";

    private EnterCourseCallBack enterCourseCallBack;

    /**传入的数据集合*/
    private List<EnterCourse> enterCourseList;

    public void setEnterCourseCallBack(EnterCourseCallBack enterCourseCallBack){
        this.enterCourseCallBack=enterCourseCallBack;
    }

    public EnterCourseItemAdapter(Context context, int itemLayout, List<EnterCourse> list){
        super(context,itemLayout,list);
        this.itemLayout=itemLayout;
        this.enterCourseList=list;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        EnterCourse enterCourse = getItem(position);
        System.out.println("当前课程信息"+enterCourse.toString());
        System.out.println("即将加入的课程信息："+enterCourse.toString());
        EnterCourseItemAdapter.ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            viewHolder = new EnterCourseItemAdapter.ViewHolder();
            viewHolder.courseCover=view.findViewById(R.id.courseCoverShow);
            viewHolder.courseName=view.findViewById(R.id.courseNameShow);
            viewHolder.courseTeacher=view.findViewById(R.id.courseTeacherShow);
            viewHolder.courseMessage=view.findViewById(R.id.courseDescriptionShow);
            viewHolder.courseCount=view.findViewById(R.id.countShow);
            viewHolder.enterCourse=view.findViewById(R.id.enterCourse);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (EnterCourseItemAdapter.ViewHolder) view.getTag();
        }
        System.out.println("课程名字："+enterCourse.getCourseName());
        viewHolder.courseName.setText(enterCourse.getCourseName());
        viewHolder.courseTeacher.setText("指导老师:"+enterCourse.getCourseTeacher());
        viewHolder.courseCount.setText(enterCourse.getStudentCount()+"人参加");
        viewHolder.courseMessage.setText(enterCourse.getCourseSynopsis());

        String ImagePath=url+enterCourse.getCourseCover();
        Glide.with(getContext())
                .load(ImagePath)
                .placeholder(R.drawable.defaultcoursebackground)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.courseCover);

        viewHolder.enterCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterCourseCallBack.enterCourse();
            }
        });

        return view;
    }

    public interface EnterCourseCallBack{
        void enterCourse();
    }


    /**内部类，对实例进行缓存*/
    class ViewHolder{
        ImageView courseCover;
        TextView courseName;
        TextView courseTeacher;
        TextView courseMessage;
        TextView courseCount;
        TextView enterCourse;
    }
}
