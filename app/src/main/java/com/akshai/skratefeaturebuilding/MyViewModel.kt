package com.akshai.skratefeaturebuilding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshai.skratefeaturebuilding.di.BaseRepository
import com.akshai.skratefeaturebuilding.di.Module
import com.akshai.skratefeaturebuilding.di.NetworkRepository
import com.akshai.skratefeaturebuilding.di.OfflineRepository
import com.akshai.skratefeaturebuilding.model.OverViewModel
import com.akshai.skratefeaturebuilding.reponse.MockApiResponse
import com.akshai.skratefeaturebuilding.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: NetworkRepository) :
    ViewModel() {

    private val _mockHeadlines = MutableLiveData<DataHandler<MockApiResponse>>()
    val moclHeadlines: LiveData<DataHandler<MockApiResponse>> = _mockHeadlines


    init {
        getMockApi()
    }

    fun getMockApi() {
        _mockHeadlines.postValue(DataHandler.LOADING())
        viewModelScope.launch {
            val response = repository.getSampleData()
            _mockHeadlines.postValue(handleResponse(response))
        }
    }

    private fun handleResponse(response: Response<MockApiResponse>): DataHandler<MockApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return DataHandler.SUCCESS(it)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }

}