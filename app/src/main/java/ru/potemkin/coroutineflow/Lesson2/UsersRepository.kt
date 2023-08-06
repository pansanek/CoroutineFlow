package ru.potemkin.coroutineflow.Lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

object UsersRepository {

    private val users = mutableListOf("Nick", "John", "Max")

    suspend fun addUser(user: String) {
        delay(10)
        users.add(user)
    }

    suspend fun loadUsers(): kotlinx.coroutines.flow.Flow<List<String>> = flow {

        while (true) {
            delay(10)
            emit(users.toList())
        }
    }
}