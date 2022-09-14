package com.akshai.skratefeaturebuilding.di

import com.akshai.skratefeaturebuilding.network.ApiService
import com.akshai.skratefeaturebuilding.reponse.MockApiResponse
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val apiService: ApiService
) :BaseRepository {

   /* suspend fun getMockApi(): Response<MockApiResponse> {

    }*/

    override  suspend fun getSampleData(): Response<MockApiResponse> {
        return apiService.getMockApi()
    }

}