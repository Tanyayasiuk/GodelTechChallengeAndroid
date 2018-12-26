package com.example.tanya.godeltechchallengeandroid.api

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.tanya.godeltechchallengeandroid.api.rest.RestService
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
abstract class ApiModule {

    @Module
    companion object {
        private val PREFS_NAME = "godeltechchallengeandroid_prefs"

        @Provides
        @JvmStatic
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return context.applicationContext.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        }

        @Provides
        @JvmStatic
        fun providesRestService(): RestService =
            Retrofit.Builder()
                .baseUrl("http://www.example.com")
                .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(RestService::class.java)
    }

    @Binds
    abstract fun bindsPreferencesApi(preferencesApiImpl: PreferencesApiImpl): ApiContract.PreferenceApi

    @Binds
    abstract fun bindsTextApi(textApiImpl: TextApiImpl): ApiContract.TextApi

}
