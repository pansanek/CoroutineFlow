package ru.potemkin.coroutineflow.lesson8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * Холодный flow - не эмитятся данные пока на них не подпишутся
 * на каждую подписку создается новый flow
 * когда коллектор перестает коллектить данные, поток прекращается
 */
val coroutineScope = CoroutineScope(Dispatchers.IO)
suspend fun main(){
    val flow = getFlow()
    val job = coroutineScope.launch {
        flow.collect{
            println(it)
        }
    }
    val job2 =coroutineScope.launch {
        flow.collect{
            println(it)
        }
    }
    job.join()
    job2.join()
}

fun getFlow(): Flow<Int> = flow{
    repeat(10){
        println("Emmited $it")
        emit(it)
        delay(1000)
    }
}