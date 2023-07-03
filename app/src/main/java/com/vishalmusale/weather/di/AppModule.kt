package com.vishalmusale.weather.di

import android.content.Context
import com.vishalmusale.weather.ui.BaseApplication
import com.vishalmusale.weather.util.Units
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) : BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString() : String {
        return "Testing Hilt"
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)=
        Units.UnitPreferences(context)
}