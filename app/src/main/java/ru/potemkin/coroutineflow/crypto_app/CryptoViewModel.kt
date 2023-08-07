package ru.potemkin.coroutineflow.crypto_app

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {


    private val repository = CryptoRepository

    private val loadingFlow = MutableSharedFlow<State>()

    val state: Flow<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart {
            emit(State.Loading)
        }
        .mergeWith(loadingFlow)

    private fun Flow<State>.mergeWith(another: Flow<State>):Flow<State>{
        return merge(this,another)
    }

    fun refreshList() {
        viewModelScope.launch {
            loadingFlow.emit(State.Loading)
            repository.refreshList()
        }
    }
}