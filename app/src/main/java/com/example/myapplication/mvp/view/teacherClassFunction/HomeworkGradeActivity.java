package com.example.myapplication.mvp.view.teacherClassFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.Bactivity.BaseActivity;
import com.hb.dialog.myDialog.MyAlertInputDialog;

public class HomeworkGradeActivity extends BaseActivity implements View.OnClickListener {

    private TextView grade1;
    /**可以输入文字的dialog*/
    private MyAlertInputDialog myAlertInputDialog;

    @Override
    protected int initLayout() {
        return R.layout.activity_homework_grade;
    }

    @Override
    protected void initView() {
        grade1=findViewById(R.id.grade1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setState(false);
        setTitle(false);
        super.onCreate(savedInstanceState);

        grade1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        myAlertInputDialog(this,"请给出分数");
    }




    /**
     * 创建班级的弹出框
     * @param context
     * @param msg
     */
    void myAlertInputDialog(Context context, String msg){
        myAlertInputDialog = new MyAlertInputDialog(context).builder()
                .setTitle(msg)
                .setEditText("");
        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok();
                myAlertInputDialog.dismiss();
            }
        }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                myAlertInputDialog.dismiss();
            }
        });
        myAlertInputDialog.show();
    }

    public void ok(){
        grade1.setText(myAlertInputDialog.getResult());
    }

    public void cancel(){

    }
}
