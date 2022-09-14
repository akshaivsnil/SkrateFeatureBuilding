package com.akshai.skratefeaturebuilding.di

import com.akshai.skratefeaturebuilding.reponse.MockApiResponse
import dagger.Provides
import retrofit2.Response

interface  BaseRepository {
     suspend fun getSampleData() : Response<MockApiResponse>
}