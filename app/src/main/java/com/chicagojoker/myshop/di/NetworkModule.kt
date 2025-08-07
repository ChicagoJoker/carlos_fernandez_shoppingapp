package com.chicagojoker.myshop.di

import com.chicagojoker.myshop.data.remote.api.ProductApi
import com.chicagojoker.myshop.data.repository.ProductRepositoryImpl
import com.chicagojoker.myshop.domain.repository.ProductRepository
import com.squareup.moshi.Moshi
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.Module
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi() : Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .client(okHttpClient)
        .addConverterFactory(retrofit2.converter.moshi.MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi =
        retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: ProductApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}