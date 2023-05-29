package com.example.plant.api

import com.example.plant.Fragment.GetUserPlantResponse
import com.example.plant.model.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {




    @POST("sensor-control/humidifier")
    fun humiControl(
        @Query("userNo") userNo: Int,
        @Query("humidifierTf") humidifierTf: Int
    ): Call<String>

    @POST("sensor-control/water-pump")
    fun pumpControl(
        @Query("userNo") userNo: Int,
        @Query("waterPumpTf") waterPumpTf: Int
    )
// User
    @GET("user/check/email")
    fun checkEmail(@Query("userId") id: String): Call<UserInfo>

    @GET("user/check/id")
    fun checkId(@Query("userId") id: String): Call<UserInfo>

    @POST("user/find/password")
    fun findPassword(
        @Body findPasswordRequest: FindPasswordRequest
    ) : Call<FindPasswordResponse>

    @POST("user/login")
    fun userLogin(
        @Body loginRequest : LoginRequest
    ) : Call<LoginCall>

    @GET("user/logout")
    fun userLogout() // 반응 보류

    @POST("user/me")
    fun userInfo() : Call<UserMeResponse>

    @GET("user/notification")
    fun userNotificationGet() : Call<UserNotificationResponse>

    @PUT("user/notification")
    fun userNotificationPut(
        @Body userNotificationPutRequest: UserNotificationPutRequest
    ) : Call<UserNotificationPutResponse>

    @POST("user/notification")
    fun userNotificationPost(
        @Body userNotificationPostRequest: UserNotificationPostRequest
    ) : Call<String>

    @DELETE("user/notification")
    fun userNotificationDelete(
        @Body userNotificationPostRequest: UserNotificationPostRequest
    ) : Call<UserNotificationDeleteResponse>

    @POST("user/register")
    fun userRegister(
        @Body userRegisterRequest: UserRegisterRequest
    ) : Call<LoginResponseFail>

    @GET("user/milage")
    fun userMilage() : Call<UserMilageResponse>

    @GET("user/my/bid")
    fun getUserBidList(
        @Query("beginPage") beginPage : Int = 0,
        @Query("pageSize") pageSize : Int = 50,
    ) : Call<GetUserBidListResponse>

    @GET("user/my/plant")
    fun getUserPlant() : Call<GetUserPlantResponse>



    // Plant

    @GET("plant")
    fun plantList(
        @Query("beginPage") beginPage: Int,
        @Query("pageSize") pageSize: Int,
        @Query("plantNo") plantNo: Int? = null,
        @Query("plantCategory") plantCategory: String? = null,
        @Query("searchKeyword") searchKeyword: String? = null
    ) : Call<PlantListResponse>

    @GET("plant/category")
    fun plantCategory() : Call<PlantCategoryResponse>

    @GET("plant-sensor")
    fun getPlantSensorList(
        @Query("warehousePlantNo") warehousePlantNo : Int,
        @Query("searchDate") searchDate : Int
    ) : Call<GetUserPlantSensorListRequest>

    // product 입찰 거래
    @GET("product")
    fun getProductList(
        @Query("beginPage") beginPage: Int,
        @Query("pageSize") pageSize: Int,
        @Query("tab") tab : String,
        @Query("plantNo") plantNo: Int? = null,
        @Query("searchKeyword") searchKeyword : String? = null
    ) : Call<BoardProductResponse>
    @POST("product")
    fun postProduct(
        @Body postProductList: PostProductList
    ) : Call<String>

    @DELETE("product")
    fun deleteProductList(
        @Query("productNo") productNo : Int
    )


    @GET("product/grades/{plantNo}")
    fun getProductGradesList(
        @Path("plantNo") plantNo : Int,
        @Query("productType") productType : Int,
        @Query("productInstant") productInstant : Int
    ) : Call<GetProductGradesListResponse>

    @GET("product/type/{productActive}")
    fun getProductTypeList(
        @Path("productActive") productActive : String,
        @Query("productType") productType : Int,
        @Query("plantNo") plantNo : Int
    ) : Call<GetProductTypeListResponse>



    //AI(사용X)
/*
    @GET("ai/bid")
    fun getBidList(
        @Query("beginPage") beginPage: Int,
        @Query("pageSize") pageSize: Int,
        @Query("plantNo") plantNo: Int
    ) : Call<GetBidListResponse>

*/


    @GET("ai/plant/color-analysis")
    fun plantImage(
        @Query("beginPage") beginPage: Int,
        @Query("pageSize") pageSize: Int,
        @Query("plantNo") plantNo: Int? = null,
        @Query("plantWarehouseNo") plantWarehouseNo: Int,
        @Query("searchKeyword") searchKeyword: String? = null
    ) : Call<PlantImageGetResponse>

    @GET("bid")
    fun getBidList(
        @Query("plantNo") plantNo: Int,
        @Query("searchDate") searchDate: Int,
        @Query("plantScoreNo") plantScoreNo: Int
    ) : Call<BidListGetResponse>

    @POST("bid")
    fun postBidList(
        @Body postBidRequest: PostBidRequest
    ) : Call<String>

    // AI 관련

    @GET("state_data")
    fun stateData() : Call<AiStateDataResponse>

    @GET("price_data")
    fun priceData() : Call<AiPriceDataResponse>



}


