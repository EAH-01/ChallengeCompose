package com.alonso.data.di


import com.alonso.data.datasource.HomeDataSource
import com.alonso.data.repository.HomeRepositoryImp
import com.alonso.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/*
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImp): HomeRepository
}



*/

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideHomeRepository(homeDataSource: HomeDataSource): HomeRepository {
        return HomeRepositoryImp(homeDataSource)
    }


}