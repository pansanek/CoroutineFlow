package ru.potemkin.coroutineflow.lesson9

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 *Горячий поток
 * эмитит значение независимо от наличия подписчиков
 * подписчики получают одни и те же элементы
 * если подписчикам не нужны данные поток продолжит эммитить данные
 * поток не завершится никогда
 */
val coroutineScope = CoroutineScope(Dispatchers.IO)
suspend fun main(){
    val flow = MutableSharedFlow<Int>()

    coroutineScope.launch {
        repeat(10){
            println("Emmited $it")
            flow.emit(it)
            delay(1000)
        }
    }
    val job = coroutineScope.launch {
        flow.first().let{
            println("Got from 1st collector $it")
        }
    }
    delay(5000)
    val job2 =coroutineScope.launch {
        flow.collect{
            println("Got from 2nd collector $it")
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