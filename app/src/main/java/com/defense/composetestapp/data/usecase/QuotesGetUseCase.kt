package com.defense.composetestapp.data.usecase

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.defense.composetestapp.data.entity.data.Candle
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class QuotesGetUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun perform(): Result<List<Candle>> = kotlin.runCatching {
        withContext(Dispatchers.IO) {
            val candles = mutableListOf<Candle>()
            context.assets.open("quotes.txt").use {
                it.bufferedReader().forEachLine { line ->
                    val splitStrings = line.split(" ")

                    val year = splitStrings[0].substring(0, 4).toInt()
                    val month = splitStrings[0].substring(4, 6).toInt()
                    val day = splitStrings[0].substring(6, 8).toInt()
                    val hour = splitStrings[1].substring(0, 2).toInt()
                    val minute = splitStrings[1].substring(2, 4).toInt()

                    val dateTime = LocalDateTime.of(year, month, day, hour, minute)
                    val open = splitStrings[2].toFloat()
                    val high = splitStrings[3].toFloat()
                    val low = splitStrings[4].toFloat()
                    val close = splitStrings[5].toFloat()

                    candles.add(Candle(dateTime, open, close, high, low))
                }
                candles.sort()
            }
            candles
        }
    }
}