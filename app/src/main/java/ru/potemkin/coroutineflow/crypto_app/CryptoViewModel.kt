package ru.potemkin.coroutineflow.crypto_app

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    val state: LiveData<State> = repository.getCurrencyList()

        .filter { it.isNotEmpty() }
        .map { State.Content(it) as State}
        .onStart {
            emit(State.Loading)
        }
        .asLiveData()



}