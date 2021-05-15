package com.example.foodmarket.network

import com.example.foodmarket.model.response.Wrapper
import com.example.foodmarket.model.response.checkout.CheckoutResponse
import com.example.foodmarket.model.response.home.HomeResponse
import com.example.foodmarket.model.response.login.LoginResponse
import com.example.foodmarket.model.response.transaction.TransactionResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
       @Field("email") email: String,
       @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(
       @Field("name") name: String,
       @Field("email") email: String,
       @Field("password") password: String,
       @Field("password_confirmation") password_confirmation: String,
       @Field("address") address: String,
       @Field("city") city: String,
       @Field("houseNumbar") houseNumbar: String,
       @Field("phoneNumber") phoneNumber: String
    ): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(
       @Part profileImage: MultipartBody.Part
    ): Observable<Wrapper<Any>>

    @GET("food")
    fun home(): Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
      @Field("food_id") email: String,
      @Field("user_id") user_id: String,
      @Field("quantity") quantity: String,
      @Field("total") total: String,
      @Field("status") status: String
    ): Observable<Wrapper<CheckoutResponse>>

    @GET("transaction")
    fun transaction(): Observable<Wrapper<TransactionResponse>>
}
