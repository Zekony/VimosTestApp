package com.zekony.data.products.di

import android.content.Context
import androidx.room.Room
import com.zekony.data.products.db.ProductsDao
import com.zekony.data.products.db.ProductsDatabase
import com.zekony.data.products.network.ApiService
import com.zekony.data.products.repositories.ApiProductsRepositoryImpl
import com.zekony.data.products.repositories.DbProductsRepositoryImpl
import com.zekony.domain.products.repositories.ApiProductsRepository
import com.zekony.domain.products.repositories.DbProductsRepository
import com.zekony.utilities.Constants.BASE_URL
import com.zekony.utilities.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {


    @Provides
    @Singleton
    fun provideDbProductsRepository(dao: ProductsDao, dispatcher: CoroutineContext) : DbProductsRepository {
        return DbProductsRepositoryImpl(dao, dispatcher)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    @Singleton
    fun provideProductsDao(database: ProductsDatabase): ProductsDao {
        return database.getProductsDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProductsDatabase {
        return Room.databaseBuilder(context, ProductsDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideApiProductsRepository(
        apiService: ApiService,
        @ApplicationContext context: Context
    ): ApiProductsRepository {
        return ApiProductsRepositoryImpl(
            apiService = apiService,
            context = context
        )
    }


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}