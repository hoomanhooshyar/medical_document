package com.patientinfo.hooman.patientinfo.Data;
import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("sms.php")
    Single<String> sendNumber(
           @Field("number[]") ArrayList<String> numbers,
           @Field("text") String text
    );
}
