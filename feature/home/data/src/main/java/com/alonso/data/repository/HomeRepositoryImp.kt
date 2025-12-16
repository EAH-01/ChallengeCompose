package com.alonso.data.repository

import com.alonso.data.datasource.HomeDataSource
import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import com.alonso.domain.repository.HomeRepository
import com.alonso.data.local.db.CoffeeDao

class HomeRepositoryImp(
    val homeDataSource: HomeDataSource,
    private val coffeeDao: CoffeeDao
) : HomeRepository {
    override suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network> {
        val response = homeDataSource.getCoffeesByCategory(category)
        insertCoffees(response.data, category)
        return response
    }

    private suspend fun insertCoffees(data: List<CoffeeEntity>?, category: String) {
        if (coffeeDao.hasCoffees().not() && category == "all") {
            data?.let { data ->
                coffeeDao.insertCoffees(
                    data.map {
                        com.alonso.data.local.db.CoffeeEntity(
                            id = it.id,
                            name = it.name,
                            price = it.price,
                            description = it.description,
                            image = it.image,
                            ranking = it.ranking,
                            category = it.category,
                            volume = it.volume,
                            favorite = it.favorite
                        )
                    }
                )
            }
        }

    }
}