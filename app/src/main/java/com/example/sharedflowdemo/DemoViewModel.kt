package com.example.sharedflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {

    // ✅ Создание MutableSharedFlow
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow: SharedFlow<Int> = _sharedFlow.asSharedFlow()

    init {
        sharedFlowInit()
    }

    fun sharedFlowInit() {
        sharedFlowLaunch()
    }

    private fun sharedFlowLaunch() {
        // ✅ Запуск корутины для эмита значений
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(200)  // Задержка 200 мс
                println("Emitting $i")  // Отладочный вывод
                _sharedFlow.emit(i)
            }
        }
    }
}