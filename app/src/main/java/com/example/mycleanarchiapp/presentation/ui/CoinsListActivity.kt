package com.example.mycleanarchiapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.example.mycleanarchiapp.R
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.presentation.viewmodels.CoinsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinsListActivity : AppCompatActivity() {

    private val mViewModel : CoinsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_list)

        lifecycleScope.launch {
            mViewModel.coinListState.collect {result->
                when(result) {
                    is Resource.Success -> {
                        Log.d("test---","coins size ${result.data?.size}")
                    }
                    is Resource.Error -> {
                        Log.d("test---","${result.message}")
                    }
                    is Resource.Loading -> {
                        Log.d("test---","Loading")
                    }
                }
            }
        }

        lifecycleScope.launch{
            mViewModel.toastCompleteCoinList.collectLatest {
//                Toast.makeText(CoinsListActivity,it,Toast.LENGTH_SHORT).show()
                Log.d("test---","$it")
            }
        }
    }
}