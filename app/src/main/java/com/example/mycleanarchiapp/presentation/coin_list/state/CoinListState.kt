package com.example.mycleanarchiapp.presentation.coin_list.state

import com.example.mycleanarchiapp.domain.model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coins : List<Coin> = emptyList(),
    val error : String = ""
)
