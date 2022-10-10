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
                    name = R.string.dynamo_magic_show,
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 1,
                    eventType = "Technology",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = R.string.the_wizard_of_oz_show,
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
                    name = R.string.real_madrid_expo,
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 3,
                    eventType = "Music",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = R.string.the_master_of_illusions,
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 3,
                    eventType = "Technology",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = R.string.dynamo_magic_show,
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 3,
                    eventType = "Music",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = R.string.the_master_of_illusions,
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 3,
                    eventType = "Technology",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = R.string.real_madrid_expo,
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 3,
                    eventType = "Music",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = R.string.the_wizard_of_oz_show,
                    placeName = "Delhi",
                    priceFrom = 100,
                    priceTo = 150,
                    dateFrom = "Sep 10",
                    dateTo = "Sep 20"
                ),
                Event(
                    id = 3,
                    eventType = "Technology",
                    typeImage = -1,
                    imageUrl = "https://cdn.arstechnica.net/wp-content/uploads/2018/06/macOS-Mojave-Dynamic-Wallpaper-transition.jpg",
                    name = R.string.real_madrid_expo,
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