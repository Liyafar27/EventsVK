package ru.example.myfirstkotlinapp.utils

import android.widget.DatePicker
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun longToDate(long: Long): Date {
        return Date(long)
    }
}

fun convertLongToTime(time: Long):Date {
       return Date(time)}

