package com.kbstar.c05parcellable;

// Parcelableb interface 구현

import android.os.Parcel;
import android.os.Parcelable;

public class MyData implements Parcelable {
    private String name;
    private int age;

    public MyData()
    {
        this.name = "김국민";
        this.age = 11;
    }

    public MyData(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("+++ MyData(String, age) ++++++++++++" + name + ", age = " + age) ;
    }

    public MyData(Parcel src) {
        this.name = src.readString();
        this.age = src.readInt();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // CREATOR 상수 정의
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // 데이터 직렬화라, 이 순서가 중요!!!
        // 이 부분의 문제였습니다.
        parcel.writeString(name);
        parcel.writeInt(age);
    }
}
