package com.kbstar.l01retroifit;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    // https://reqres.in/api/login POST

    @POST("api/login")
    Call<ResLoginData> requestPostLogin(@Body ReqLoginData reqLoginData);

    /*
        http://test.com/api/login/a.php?id=test&pass=1111

     */

    @GET("api/users")
    Call<ResUserData> requestGetUsers(@Query(value="page", encoded=true) String page);
}
