package com.hammad.riyadh.repository

import com.hammad.riyadh.data.MockData
import com.hammad.riyadh.models.Event
import com.hammad.riyadh.models.TabItem
import javax.inject.Inject

interface CoreRepository {

    fun getEventsData(): List<Event>
    fun getTabsData(): List<TabItem>

    class Impl @Inject constructor(private val mockData: MockData): CoreRepository {

        override fun getEventsData(): List<Event> {
            return mockData.getEventsData()
        }

        override fun getTabsData(): List<TabItem> {
            return mockData.getTabData()
        }

    }


}