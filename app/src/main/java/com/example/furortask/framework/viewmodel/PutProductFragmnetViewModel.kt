package com.example.furortask.framework.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.furortask.App
import com.example.furortask.business.data.remote.model.PutProductRequstModel
import com.example.furortask.business.util.BaseDomen
import com.example.furortask.business.util.NetworkStatus
import com.example.furortask.business.util.SingleLiveEvent
import com.example.furortask.framework.repo.MainRepo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class PutProductFragmnetViewModel(
    private val repository: MainRepo,
    app: Application
) : AndroidViewModel(app) {
    private val _livePutProductState = MediatorLiveData<NetworkStatus<Any>>()
    val livePutProductState: LiveData<NetworkStatus<Any>> =
        _livePutProductState
    val toast = SingleLiveEvent<String>()
    val TAG = "TAG"

    fun putProduct(model: PutProductRequstModel) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "putProduct: Loading")
                _livePutProductState.postValue(NetworkStatus.LOADING())
                val result = repository.putProduct(model)
                if (result.isSuccessful) {
                    when (result.code()) {
                        BaseDomen.SUCCESS-> {
                            _livePutProductState.postValue(NetworkStatus.SUCCESS(result))
                        }
                        else->
                        {
                            _livePutProductState.postValue(NetworkStatus.ERROR("Error"))
                        }
                    }

                }
            } catch (e: HttpException)
            {
                Toast.makeText(App.context, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}