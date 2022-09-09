package com.example.mycleanarchiapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.domain.model.Coin
import com.example.mycleanarchiapp.domain.use_case.GetCoinsUseCase
import com.example.mycleanarchiapp.presentation.coin_list.state.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel(){

    private val _CoinListState = mutableStateOf(CoinListState())

    val coinListState : State<CoinListState> = _CoinListState

    private val _toastCompleteCoinList = MutableSharedFlow<String>()
    val toastCompleteCoinList : SharedFlow<String> = _toastCompleteCoinList.asSharedFlow()

    init {
        getCoins()

    }

    private fun getCoins() {
        val coins = getCoinsUseCase()
        viewModelScope.launch {
            coins.collect{result->
                when(result) {
                    is Resource.Success -> {
                        _CoinListState.value = CoinListState(coins = result.data ?: emptyList())
                        completeCoinList()
                    }
                    is Resource.Error -> {
                        _CoinListState.value = CoinListState(error = result.message ?: "An unexpected error occurred!")
                    }
                    is Resource.Loading -> {
                        _CoinListState.value = CoinListState(isLoading = true)
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