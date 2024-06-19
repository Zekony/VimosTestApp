package com.zekony.data.registration.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.zekony.data.registration.AuthRepositoryImpl
import com.zekony.domain.registration.AuthRepository
import com.zekony.utilities.Constants.DATA_STORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule {

    private val Context.dataStore by preferencesDataStore(
        name = DATA_STORE_NAME
    )

    @Provides
    @Singleton
    fun provideAuthRepository(dataStore: DataStore<Preferences>, dispatcher: CoroutineContext): AuthRepository {
        return AuthRepositoryImpl(dataStore, dispatcher)
    }

    @Provides
    @Singleton
    fun provideDatastore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}