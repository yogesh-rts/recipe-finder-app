package com.ykcoding.recipefinderapp.helper

import java.time.Duration

object TimeUtils {

    fun formatTime(minutes: Int): String {
        val duration = Duration.ofMinutes(minutes.toLong())
        val hours = duration.toHours()
        val mins = duration.minusHours(hours).toMinutes()

        return when {
            (hours > 0 && mins > 0 ) -> "$hours hr $mins mins"
            (hours > 0) -> "$hours h"
            else -> "$mins mins"
        }
    }
}