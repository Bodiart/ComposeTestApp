package com.defense.composetestapp.di.module

import com.defense.composetestapp.data.api.ContactsService
import com.defense.composetestapp.di.DI_NAMED_BASE_URL
import com.defense.composetestapp.util.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Named(DI_NAMED_BASE_URL)
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    fun provideRetrofit(
        @Named(DI_NAMED_BASE_URL) baseUrl: String,
        gson: Gson
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideContactsService(retrofit: Retrofit): ContactsService =
        retrofit.create(ContactsService::class.java)
}