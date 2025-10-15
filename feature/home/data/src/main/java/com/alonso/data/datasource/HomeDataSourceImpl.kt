package com.alonso.data.datasource


import com.alonso.data.api.HomeApi
import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.entity.CoffeeEntity
import javax.inject.Inject

internal class HomeDataSourceImpl @Inject constructor(
    private val api: HomeApi
) : HomeDataSource {

    override suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network> {

        return ResultDataEntity.success(
            listOf(
                CoffeeEntity(
                    id = 1,
                    name = "Coffer 1",
                    price = 24.00,
                    description = "Lore inpis test",
                    image = "",
                    vote = 4
                )
            )
        )
        /*return safeApiCall { api.getCofferByCategory() }
            .accessDataToMap { data ->
                data?.map {
                    CoffeeEntity(
                        id = it.id,
                        name = it.name,
                        price = it.price,
                        description = it.description,
                        image = it.image,
                        vote = it.vote,
                    )
                }
            }*/

    }
}