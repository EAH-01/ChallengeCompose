package com.alonso.data.repository

import com.alonso.data.datasource.HomeDataSource
import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import com.alonso.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(val homeDataSource: HomeDataSource) : HomeRepository {
    override suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network> {
        return homeDataSource.getCoffeesByCategory(category)
    }
}