package com.akshai.skratefeaturebuilding.network

import com.akshai.skratefeaturebuilding.reponse.MockApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/bb11aecd-ba61-44b9-9e2c-beabc442d818")
    suspend fun getMockApi() : Response<MockApiResponse>
}