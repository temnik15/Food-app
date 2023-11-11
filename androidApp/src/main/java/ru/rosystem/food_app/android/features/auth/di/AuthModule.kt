package ru.rosystem.food_app.android.features.auth.di

import com.arkivanov.mvikotlin.core.BuildConfig
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dagger.Module
import dagger.Provides

@Module
internal class AuthModule {


    @AuthScope
    @Provides
    fun provideStoreFactory(): StoreFactory {
        return if (BuildConfig.DEBUG) TimeTravelStoreFactory() else DefaultStoreFactory()
    }
}