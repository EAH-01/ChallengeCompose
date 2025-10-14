package com.alonso.navigation

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Binds
    @Singleton
    fun provideComposeNavigator(
        myShopComposeNavigator: CoffeeNavigator,
    ): AppNavigator
}