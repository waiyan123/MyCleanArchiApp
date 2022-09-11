package com.example.mycleanarchiapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycleanarchiapp.presentation.Screen
import com.example.mycleanarchiapp.presentation.coin_detail.components.CoinDetailScreen
import com.example.mycleanarchiapp.presentation.coin_list.components.CoinListScreen
import com.example.mycleanarchiapp.presentation.theme.MyCleanArchiAppTheme
import com.example.mycleanarchiapp.presentation.viewmodels.CoinDetailViewModel
import com.example.mycleanarchiapp.presentation.viewmodels.CoinsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mViewModel : CoinsListViewModel by viewModels()
    val coinDetailViewModel : CoinDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

                        composable(
                            route = Screen.CoinDetailScreen.route +"/{coinId}"
                        ) {
                            CoinDetailScreen(viewModel = coinDetailViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting(name = "Waiyan")
}