package com.example.mycleanarchiapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.mycleanarchiapp.R
import com.example.mycleanarchiapp.presentation.viewmodels.CoinsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsListActivity : AppCompatActivity() {

    private val mViewModel : CoinsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val state = mViewModel.state.value
        Log.d("test---","Coins List size "+state.coins.size)
    }
}