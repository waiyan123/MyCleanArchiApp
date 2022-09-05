package com.example.mycleanarchiapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.mycleanarchiapp.R
import com.example.mycleanarchiapp.common.Resource
import com.example.mycleanarchiapp.presentation.viewmodels.CoinsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsListActivity : AppCompatActivity() {

    private val mViewModel : CoinsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.coinsListLivedata.observe(this) {
            Log.d("test---","coins size "+it.coins.size)
            Log.d("test---","error "+it.error)
            Log.d("test---","loading "+it.isLoading)
        }
    }
}