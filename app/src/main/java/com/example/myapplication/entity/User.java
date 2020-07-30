package com.example.myapplication.entity;


import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String telephone;
    private String identity;
    private String nickName;
    private String gender;
    private String signature;
    private String headImage;
    private String result;

}
