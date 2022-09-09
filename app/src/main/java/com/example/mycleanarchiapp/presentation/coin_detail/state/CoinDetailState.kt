package com.example.mycleanarchiapp.presentation.coin_detail.state

import com.example.mycleanarchiapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coins : CoinDetail? = null,
    val error : String = ""
)
