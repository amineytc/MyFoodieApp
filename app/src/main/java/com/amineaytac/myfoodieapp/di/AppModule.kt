package com.amineaytac.myfoodieapp.di

import android.app.Application
import androidx.room.Room
import com.amineaytac.myfoodieapp.data.source.local.MealDao
import com.amineaytac.myfoodieapp.data.source.local.MealDatabase
import com.amineaytac.myfoodieapp.data.source.remote.MealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val REST_API_BASE_URL="https://www.themealdb.com/api/json/v1/1/"

    @Provides
    @Singleton
    fun provideLoginInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpCilent(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(REST_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMealApi(retrofit: Retrofit) : MealApi {
        return retrofit.create(MealApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : MealDatabase=
        Room.databaseBuilder(app,MealDatabase::class.java,"meal.db").build()

    @Provides
    @Singleton
    fun provideDao(database:MealDatabase):MealDao{
        return database.getMealFromDao()
    }
}