package com.aiden.aiden.architecturepatternstudy.di

import androidx.room.Room
import com.aiden.aiden.architecturepatternstudy.data.source.DefaultUpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.local.UpbitDatabase
import com.aiden.aiden.architecturepatternstudy.data.source.local.UpbitLocalDataSource
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun getRepositoryModule() = module {
    single {
        UpbitRemoteDataSource(get())
    }
    single {
        UpbitLocalDataSource(get())
    }
    single {
        Room.databaseBuilder(
            androidApplication(),
            UpbitDatabase::class.java, "upbit-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<UpbitRepository> {
        DefaultUpbitRepository(
            get() as UpbitRemoteDataSource,
            get() as UpbitLocalDataSource
        )
    }
}
