package ru.potemkin.coroutineflow.lesson14

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

suspend fun main(){
    val scope = CoroutineScope(Dispatchers.Default)

    val flow = MutableStateFlow(0)

    val producer = scope.launch {
        delay(500)
        repeat(10){
            println("Emitted: $it")
            flow.emit(1)
            println("After emit $it")
            delay(200)
        }
    }
    val consumer = scope.launch{
        flow.collectLatest{
            println("Collecting: $it")
            delay(5000)
            println("Collected: $it")
        }
    }

    producer.join()
    consumer.join()
}