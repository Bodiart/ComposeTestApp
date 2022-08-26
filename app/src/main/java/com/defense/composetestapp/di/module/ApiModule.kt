package com.defense.composetestapp.di.module

import com.defense.composetestapp.data.api.ContactsService
import com.defense.composetestapp.data.api.UnsplashService
import com.defense.composetestapp.di.DI_NAMED_BASE_RETROFIT
import com.defense.composetestapp.di.DI_NAMED_BASE_URL
import com.defense.composetestapp.di.DI_NAMED_UNSPLASH_BASE_RETROFIT
import com.defense.composetestapp.di.DI_NAMED_UNSPLASH_BASE_URL
import com.defense.composetestapp.util.BASE_URL
import com.defense.composetestapp.util.UNSPLASH_BASE_URL
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

}

@InstallIn(SingletonComponent::class)
@Module
class BaseApiModule {
    @Provides
    @Named(DI_NAMED_BASE_URL)
    fun provideBaseUrl(): String = BASE_URL

    @Named(DI_NAMED_BASE_RETROFIT)
    @Provides
    fun provideRetrofit(
            @Named(DI_NAMED_BASE_URL) baseUrl: String,
            gson: Gson
    ): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideContactsService(@Named(DI_NAMED_BASE_RETROFIT) retrofit: Retrofit): ContactsService =
            retrofit.create(ContactsService::class.java)
}

@InstallIn(SingletonComponent::class)
@Module
class UnsplashApiModule {
    @Provides
    @Named(DI_NAMED_UNSPLASH_BASE_URL)
    fun provideUnsplashBaseUrl(): String = UNSPLASH_BASE_URL

    @Named(DI_NAMED_UNSPLASH_BASE_RETROFIT)
    @Provides
    fun provideRetrofit(
            @Named(DI_NAMED_UNSPLASH_BASE_URL) baseUrl: String,
            gson: Gson
    ): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideContactsService(@Named(DI_NAMED_UNSPLASH_BASE_RETROFIT) retrofit: Retrofit): UnsplashService =
            retrofit.create(UnsplashService::class.java)
}