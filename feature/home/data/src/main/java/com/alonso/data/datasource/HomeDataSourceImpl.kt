package com.alonso.data.datasource


import com.alonso.data.api.HomeApi
import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import com.alonso.domain.accessDataToMap

import com.alonso.domain.entity.CoffeeEntity
import com.alonso.network.di.network.network.safeApiCall
import javax.inject.Inject

internal class HomeDataSourceImpl @Inject constructor(
    private val api: HomeApi
) : HomeDataSource {

    override suspend fun getCoffeesByCategory(category: String): ResultDataEntity<List<CoffeeEntity>?, ErrorEntity.Network> {
        return safeApiCall { api.getCofferByCategory(category) }
            .accessDataToMap { data ->
                data?.map {
                    CoffeeEntity(
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
            }

    }
}

/*  return ResultDataEntity.success(
            listOf(
                CoffeeEntity(
                    id = "1",
                    name = "Espresso 1",
                    price = 2.50,
                    description = "Un shot concentrado de café con un sabor intenso y una crema rica.1",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 5.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "2",
                    name = "Latte Macchiato 2",
                    price = 3.75,
                    description = "Leche vaporizada manchada con un toque de espresso, suave y cremoso.2",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 4.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "3",
                    name = "Cappuccino 3",
                    price = 3.50,
                    description = "La combinación perfecta de espresso, leche vaporizada y una generosa capa de espuma. 3",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 4.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "4",
                    name = "Americano 4",
                    price = 3.00,
                    description = "Un shot de espresso diluido con agua caliente, resultando en un café suave. 4",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 3.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "5",
                    name = "Mocha 5",
                    price = 4.25,
                    description = "Una deliciosa mezcla de espresso, leche vaporizada y sirope de chocolate. 5",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 5.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "6",
                    name = "Flat White 6",
                    price = 3.85,
                    description = "Originario de Australia, combina espresso con una microespuma de leche sedosa.6",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 4.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "7",
                    name = "Caramel Macchiato",
                    price = 4.50,
                    description = "Leche vaporizada con un toque de vainilla, coronada con espresso y sirope de caramelo. Leche vaporizada con un toque de vainilla, coronada con espresso y . 7",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 5.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "8",
                    name = "Cold Brew 8",
                    price = 4.00,
                    description = "Café infusionado en frío durante horas para un sabor suave, menos ácido y con más cafeína. 8",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 4.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "9",
                    name = "Ristretto 9",
                    price = 2.75,
                    description = "Un shot de espresso aún más corto y concentrado, extrayendo los sabores más dulces. 9",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 5.0,
                    category = "Coffee"
                ),
                CoffeeEntity(
                    id = "10",
                    name = "Irish Coffee 10",
                    price = 6.50,
                    description = "Café caliente mezclado con whisky irlandés y azúcar, y cubierto con crema batida. 10",
                    image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                    ranking = 4.0,
                    category = "Coffee"
                )
            )
        )*/