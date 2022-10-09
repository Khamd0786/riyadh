package com.hammad.riyadh.data

import com.hammad.riyadh.R
import com.hammad.riyadh.models.Event
import com.hammad.riyadh.models.TabItem

interface MockData {
    fun getEventsData(): List<Event>
    fun getTabData(): List<TabItem>

    class Impl() : MockData {
        override fun getEventsData(): List<Event> {
            return listOf(
                Event(
                    id = 0,
                    eventType = "Magic",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = "Dynamo Magic Show",
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 1,
                    eventType = "Magic",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = "Dynamo Magic Show",
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 2,
                    eventType = "Magic",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = "Dynamo Magic Show",
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 3,
                    eventType = "Magic",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = "Dynamo Magic Show",
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                )
            )
        }

        override fun getTabData(): List<TabItem> {
            return listOf(
                TabItem("All", true),
                TabItem("Magic", false),
                TabItem("Technology", false),
                TabItem("Music", false)
            )
        }

    }

}