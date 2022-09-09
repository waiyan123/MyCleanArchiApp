package com.example.mycleanarchiapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycleanarchiapp.R
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.presentation.Screen
import com.example.mycleanarchiapp.presentation.coin_detail.components.CoinListScreen
import com.example.mycleanarchiapp.presentation.theme.MyCleanArchiAppTheme
import com.example.mycleanarchiapp.presentation.viewmodels.CoinsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinsListActivity : AppCompatActivity() {

    private val mViewModel: CoinsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_coin_list)

        setContent {
            MyCleanArchiAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController = navController, viewModel = mViewModel)
                        }
                    }
                }
            }
        }

//        lifecycleScope.launch {
//            mViewModel.coinListState.collect { result ->
//                when (result) {
//                    is Resource.Success -> {
//                        Log.d("test---", "coins size ${result.data?.size}")
//                    }
//                    is Resource.Error -> {
//                        Log.d("test---", "${result.message}")
//                    }
//                    is Resource.Loading -> {
//                        Log.d("test---", "Loading")
//                    }
//                }
//            }
//        }
//
//        lifecycleScope.launch {
//            mViewModel.toastCompleteCoinList.collectLatest {
////                Toast.makeText(CoinsListActivity,it,Toast.LENGTH_SHORT).show()
//                Log.d("test---", "$it")
//            }
//        }
    }
}