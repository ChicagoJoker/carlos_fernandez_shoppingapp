package com.chicagojoker.myshop.di

import com.chicagojoker.myshop.domain.repository.ProductRepository
import com.chicagojoker.myshop.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        repository: ProductRepository
    ) : GetProductsUseCase = GetProductsUseCase(repository)
}