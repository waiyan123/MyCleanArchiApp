package com.example.mycleanarchiapp.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mycleanarchiapp.common.Constants
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.domain.model.CoinDetail
import com.example.mycleanarchiapp.domain.use_case.GetCoinUseCase
import com.example.mycleanarchiapp.presentation.coin_detail.state.CoinDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {

        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)

        }
        Log.d("test---","viewmodel init ${savedStateHandle.get<String>(Constants.PARAM_COIN_ID)}")
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("test---","coin name "+result.data?.name)
                    _state.value = CoinDetailState(
                        coin = result.data ?: CoinDetail(
                            "", "", "", "", 0, false,
                            emptyList(), emptyList()
                        )
                    )
                }
                is Resource.Error -> {
                    Log.d("test---","error "+result.message)
                    _state.value =
                        CoinDetailState(error = result.message ?: "An expected error occurred")
                }
                is Resource.Loading -> {
                    Log.d("test---","loading ")
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }
    }
}