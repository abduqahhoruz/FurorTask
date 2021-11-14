package com.example.furortask.framework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.furortask.framework.repo.MainRepo

class ProductTypeViewModel(
    private val repository: MainRepo,
    app: Application
) : AndroidViewModel(app) {
    //private val getProductTypeMapper: IGetProductTypesMapper by lazy { GetProductTypesMapperImpl() }
//    private val _liveSpecialityState =
//        MediatorLiveData<NetworkStatus<List<GetProductTypeModelLocal>>>()
//    val liveSpecialityState: LiveData<NetworkStatus<List<GetProductTypeModelLocal>>> =
//        _liveSpecialityState

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
    fun  getProduct()
    {

    }
}

