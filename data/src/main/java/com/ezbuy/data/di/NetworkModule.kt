package com.ezbuy.data.di

import android.os.Build
import com.ezbuy.data.BuildConfig
import com.ezbuy.data.network.ConnectivityManagerNetworkMonitor
import com.ezbuy.data.network.NetworkMonitor
import com.ezbuy.data.remote.EzbuyAPI
import com.ezbuy.data.remote.EzbuyRemoteDataSource
import com.ezbuy.data.remote.EzbuyRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ezbuy.domain.model.ErrorResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): EzbuyAPI = retrofit.create(EzbuyAPI::class.java)

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor)
                .connectTimeout(Duration.ofSeconds(10))
                .readTimeout(Duration.ofSeconds(30))
                .build()
        } else {
            OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideErrorResponseJsonAdapter(moshi: Moshi): com.squareup.moshi.JsonAdapter<ErrorResponse> {
        return moshi.adapter(ErrorResponse::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkMonitorModule {
    @Binds
    @Singleton
    abstract fun bindNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): NetworkMonitor
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindEzbuyRemoteDataSource(ezbuyRemoteDataSourceImpl: EzbuyRemoteDataSourceImpl): EzbuyRemoteDataSource
}
