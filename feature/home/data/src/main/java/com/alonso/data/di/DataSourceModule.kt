package com.example.detail.data.api.di

import com.alonso.data.api.HomeApi
import com.alonso.data.datasource.HomeDataSource
import com.alonso.data.datasource.HomeDataSourceImpl
import com.alonso.data.repository.HomeRepositoryImp
import com.alonso.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideHomeDataSource(apiService: HomeApi): HomeDataSource {
        return HomeDataSourceImpl(apiService)
    }

}