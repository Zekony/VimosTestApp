package com.zekony.data.registration

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zekony.domain.registration.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AuthRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: CoroutineContext
): AuthRepository {

    private object DatastoreKeys {
        val userName = stringPreferencesKey("UserName")
    }

    override suspend fun saveUserName(name: String) {
        CoroutineScope(dispatcher).launch {
            dataStore.edit {
                it[DatastoreKeys.userName] = name
            }
        }
    }

    override fun getUserName(): Flow<String> = dataStore.data.map {
        it[DatastoreKeys.userName] ?: ""
    }.flowOn(dispatcher)

}