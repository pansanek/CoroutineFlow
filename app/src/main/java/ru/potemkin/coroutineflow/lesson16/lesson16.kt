package ru.potemkin.coroutineflow.lesson16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {
    val flow = loadDataFlow()
    flow.map {
        State.Content(it) as State
    }.onStart {
        emit(State.Loading)
    }.retry(2) {
        true
    }
//        .map {
//            throw java.lang.RuntimeException()
//            it
//        }
        .catch {
            emit(State.Error)
        }.collect() {
            when (it) {
                is State.Content -> {
                    println("Collected ${it.value}")
                }
                State.Error -> {
                    println("Error")
                }
                State.Loading -> {
                    println("Loading")
                }
            }
        }
}

fun loadDataFlow(): Flow<Int> = flow {
    repeat(5) {
        delay(500)
        emit(it)
    }
    throw java.lang.RuntimeException("flow block")

}

sealed class State {
    data class Content(val value: Int) : State()
    object Loading : State()
    object Error : State()
}

