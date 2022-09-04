package com.example.mycleanarchiapp.di

import android.content.Context
import androidx.room.Room
import com.example.mycleanarchiapp.common.Constants
import com.example.mycleanarchiapp.data.offline_database.MyRoomDatabase
import com.example.mycleanarchiapp.data.remote.CoinPaprikaApi
import com.example.mycleanarchiapp.data.repository.CoinRepositoryImpl
import com.example.mycleanarchiapp.domain.repository.CoinRepository
import com.example.mycleanarchiapp.domain.use_case.GetCoinUseCase
import com.example.mycleanarchiapp.domain.use_case.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinModule {

    @Provides
    @Singleton
    fun providesGetCoinsUseCase(coinRepository: CoinRepository) : GetCoinsUseCase{
        return GetCoinsUseCase(coinRepository)
    }

    @Provides
    @Singleton
    fun providesCoinRepository(api : CoinPaprikaApi,myRoomDatabase: MyRoomDatabase) : CoinRepository {
        return CoinRepositoryImpl(api,myRoomDatabase)
    }

    @Provides
    @Singleton
    fun providesCoinPaprikaApi() : CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGetCoinUseCase(coinRepository: CoinRepository) : GetCoinUseCase {
        return GetCoinUseCase(coinRepository)
    }

    @Provides
    @Singleton
    fun providesMyRoomDatabase(@ApplicationContext context : Context) : MyRoomDatabase{
        return Room.databaseBuilder(context,MyRoomDatabase::class.java,"My Room Database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}