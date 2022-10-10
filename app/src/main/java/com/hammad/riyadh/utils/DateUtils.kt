package com.hammad.riyadh.utils

object DateUtils {

    fun toNextLineDate(date: String): String {
        return date.replace(" ", "\n")
    }
}