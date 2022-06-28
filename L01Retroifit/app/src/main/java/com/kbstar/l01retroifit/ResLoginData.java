package com.kbstar.l01retroifit;

import com.google.gson.annotations.Expose;

public class ResLoginData {

    // object 해당하는 값이 null ==> json 필드 자동 생략
    @Expose
    private String token;

    @Override
    public String toString() {
        return "ResLoginData{" +
                "token='" + token + '\'' +
                '}';
    }
}
