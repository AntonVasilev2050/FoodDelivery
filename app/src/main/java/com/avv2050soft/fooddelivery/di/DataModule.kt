package com.avv2050soft.fooddelivery.di

import android.app.Application
import android.content.Context
import com.avv2050soft.fooddelivery.data.repository.MealsRepositoryImpl
import com.avv2050soft.fooddelivery.domain.repository.MealsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMealsRepository(): MealsRepository {
        return MealsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}