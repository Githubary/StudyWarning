package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Course implements Parcelable {
    private Integer id;
    private String courseName;
    private String courseNumber;
    private String courseTeacher;
    private String courseCover;


    protected Course(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        courseName = in.readString();
        courseNumber = in.readString();
        courseTeacher = in.readString();
        courseCover = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(courseName);
        dest.writeString(courseNumber);
        dest.writeString(courseTeacher);
        dest.writeString(courseCover);
    }
}
