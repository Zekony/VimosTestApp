package com.zekony.domain.registration

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun saveUserName(name: String)

    fun getUserName(): Flow<String>
}