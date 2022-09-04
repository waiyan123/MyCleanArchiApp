package com.example.mycleanarchiapp.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mycleanarchiapp.common.Constants
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.domain.use_case.GetCoinUseCase
import com.example.mycleanarchiapp.presentation.coin_detail.CoinDetailState
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {coinId->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId : String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coins = result.data)
                }
                is Resource.Error -> {
                    _state.value =
                        CoinDetailState(error = result.message ?: "An expected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }
    }
}