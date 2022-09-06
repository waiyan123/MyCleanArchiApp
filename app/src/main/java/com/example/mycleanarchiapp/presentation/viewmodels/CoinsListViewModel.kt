package com.example.mycleanarchiapp.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.domain.model.Coin
import com.example.mycleanarchiapp.domain.use_case.GetCoinsUseCase
import com.example.mycleanarchiapp.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel(){

    private val _CoinListState = MutableStateFlow<Resource<List<Coin>>?>(null)
    val coinListState = _CoinListState.asStateFlow()

    private val _toastCompleteCoinList = MutableSharedFlow<String>()
    val toastCompleteCoinList = _toastCompleteCoinList.asSharedFlow()

    init {
        getCoins()


    }

    private fun getCoins() {
        val coins = getCoinsUseCase()
        viewModelScope.launch {
            coins.collect{result->
                when(result) {
                    is Resource.Success -> {
                        _CoinListState.value = Resource.Success(result.data ?: emptyList())
                        completeCoinList()
                    }
                    is Resource.Error -> {
                        _CoinListState.value = Resource.Error(result.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _CoinListState.value = Resource.Loading()
                    }
                }
            }
        }
    }

    private fun completeCoinList() {
        viewModelScope.launch {
            _toastCompleteCoinList.emit("Complete updating coin list!")
        }
    }
}