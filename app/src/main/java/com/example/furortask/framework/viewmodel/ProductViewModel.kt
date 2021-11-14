package com.example.furortask.framework.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.liveData
import com.example.furortask.business.data.remote.model.GetProductResponseData
import com.example.furortask.business.util.NetworkStatus
import com.example.furortask.business.util.SingleLiveEvent
import com.example.furortask.framework.repo.MainRepo
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: MainRepo,
    app: Application
) : AndroidViewModel(app) {
    //private val getProductTypeMapper: IGetProductTypesMapper by lazy { GetProductTypesMapperImpl() }
 private val _liveSpecialityState =
       SingleLiveEvent<NetworkStatus<List<GetProductResponseData>>>()
  val liveSpecialityState: LiveData<NetworkStatus<List<GetProductResponseData>>> =
        _liveSpecialityState

   /* fun getSpeciality() {
        Log.d("TAG", "getSpeciality: ")

        viewModelScope.launch {
            try {
                _liveSpecialityState.postValue(NetworkStatus.LOADING())
                val result = repository.getProductTypes(1, 10)
                Log.d("TAG", "getSpeciality: $result")
                if (result.isSuccessful) {
                    val body = result.body()
                    val data = body
                    Log.d("TAG", "getSpeciality:$data ")
                    if (data != null) {
                        _liveSpecialityState.postValue(
                            NetworkStatus.SUCCESS(data.map { getProductTypeMapper.map(it) })
                        )
                    } else {
                        _liveSpecialityState.postValue(
                            NetworkStatus.ERROR("null")
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d("TAG", "getSpeciality: ${e.message} ")
            }

        }

    }*/
   val products = repository.getProductResult()


}

