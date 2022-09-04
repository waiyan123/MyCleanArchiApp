package com.example.mycleanarchiapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.domain.model.Coin
import com.example.mycleanarchiapp.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(private val getCoinsUseCase: GetCoinsUseCase) : ViewModel(){

    init {

    }

    fun getCoinList() {
        val coins = getCoinsUseCase()
        GlobalScope.launch {
            coins.collect {
                Log.d("test---", "coins size " + it.data!!.size)
                Log.d("test---", "error message " + it.message!!)
            }
        }
    }
}