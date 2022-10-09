package com.hammad.riyadh.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hammad.riyadh.models.Event
import com.hammad.riyadh.models.TabItem
import com.hammad.riyadh.repository.CoreRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventsViewModel @Inject constructor(private val coreRepository: CoreRepository) :
    ViewModel() {

    private val _eventShareFlow = MutableSharedFlow<List<Event>>()
    val eventsShareFlow = _eventShareFlow.asSharedFlow()

    private val _tabShareFlow = MutableSharedFlow<List<TabItem>>()
    val tabShareFlow = _tabShareFlow.asSharedFlow()

    fun loadEventsData(sortBy: String) {
        viewModelScope.launch {
            coreRepository.getEventsData()
                .filter { it.eventType.lowercase().contains(sortBy) }
                .also {
                    Log.d("TAG", "loadEventsData: $it")
                    _eventShareFlow.emit(it)
                }
        }
    }

    fun loadTabData() {
        viewModelScope.launch {
            val data = coreRepository.getTabsData()
            Log.d("TAG", "loadTabData: $data")
            _tabShareFlow.emit(data)
        }
    }



}