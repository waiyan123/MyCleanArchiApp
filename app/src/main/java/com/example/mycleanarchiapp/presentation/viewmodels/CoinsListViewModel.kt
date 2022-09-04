package com.example.mycleanarchiapp.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.domain.use_case.GetCoinsUseCase
import com.example.mycleanarchiapp.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel(){

    private val _state = mutableStateOf<CoinListState>(CoinListState())
    val state : State<CoinListState>  = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        val coins = getCoinsUseCase()
        coins.onEach {result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }
    }
}