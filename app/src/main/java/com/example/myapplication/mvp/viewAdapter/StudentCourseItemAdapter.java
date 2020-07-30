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
import com.example.myapplication.entity.Course;
import com.hb.dialog.dialog.ConfirmDialog;

import java.util.List;

public class StudentCourseItemAdapter extends ArrayAdapter<Course> {


    /**提示确认dialog*/
    private ConfirmDialog confirmDialog;

    /**courseItem布局的地方*/
    private int itemLayout;

    View view;

    String url ="http://47.92.142.206:8080/";


    /**传入的数据集合*/
    private List<Course> list;
    private QuitCourseCallBack quitCourseCallBack;

    public void setQuitCourseCallBack(QuitCourseCallBack quitCourseCallBack){
        this.quitCourseCallBack=quitCourseCallBack;
    }

    public StudentCourseItemAdapter(Context context, int itemLayout, List<Course> list){
        super(context,itemLayout,list);
        this.itemLayout=itemLayout;
        this.list=list;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        Course course = getItem(position);
        System.out.println(course.toString());
        ViewHolder viewHolder;
        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.courseCover=view.findViewById(R.id.stu_course_image);
            viewHolder.courseName=view.findViewById(R.id.course_name);
            viewHolder.courseTeacher=view.findViewById(R.id.course_teacher);
            viewHolder.deleteStudentCourse=view.findViewById(R.id.delete_course);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.courseName.setText(course.getCourseName());
        viewHolder.courseTeacher.setText(course.getCourseTeacher());

        String ImagePath=url+course.getCourseCover();

        System.out.println(ImagePath);

        Glide.with(getContext())
                .load(ImagePath)
                .placeholder(R.drawable.defaultcoursebackground)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.courseCover);


        /**item内部删除按钮，将整个item删除*/
        viewHolder.deleteStudentCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(getContext(),position);
            }
        });
        return view;
    }

    private void confirmDelete(final Context context, final int position) {
        this.confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("确认退出该门课程吗？");
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {

            @Override
            public void ok() {
                System.out.println("即将退出的course："+list.get(position).toString());
                String courseNumber = list.get(position).getCourseNumber();
                list.remove(position);
                quitCourseCallBack.QuitCourse(courseNumber);
                /**刷新这个适配器*/
                StudentCourseItemAdapter.this.notifyDataSetChanged();
            }

            @Override
            public void cancel() {

            }
        });
        confirmDialog.show();
    }

    public interface QuitCourseCallBack{
        void QuitCourse(String courseNumber);
    }

    /**内部类，对实例进行缓存*/
    class ViewHolder{
        ImageView courseCover;
        TextView courseName;
        TextView courseTeacher;
        TextView deleteStudentCourse;
    }

}
