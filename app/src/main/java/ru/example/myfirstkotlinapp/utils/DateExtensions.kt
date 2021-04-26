package ru.example.myfirstkotlinapp.utils

import androidx.room.TypeConverter
import ru.example.myfirstkotlinapp.model.User
import java.util.*

fun Date.removeTime(): Date {
    val cal = Calendar.getInstance() // get calendar instance
    cal.time = this
    cal[Calendar.DAY_OF_MONTH] = 1
    cal[Calendar.HOUR_OF_DAY] = 0
    cal[Calendar.MINUTE] = 0
    cal[Calendar.SECOND] = 0
    cal[Calendar.MILLISECOND] = 0

    return cal.time
}

fun Date.getMonthInt(): Int {
    val m = Calendar.getInstance()
    m.time = this

    return m.get(Calendar.MONTH)
}

fun Date.getYearInt(): Int {
    val m = Calendar.getInstance()
    m.time = this

    return m.get(Calendar.YEAR)
}

fun Map<Date, Int>.filterYearForDate(year: Int) = filter {
    it.key.getYearInt() == year
}

fun Map<User, Date>.filterYearForUserDate(year: Int) = filter {
    it.value.getYearInt() == year
}

fun certainMonthForUserDate(arrayIntent: Map<User, Date>, month: Int): Map<User, Date> {
    return arrayIntent.filter { it.value.getMonthInt() == month }
}

fun certainMonthForDate(dateGrouping: Map<Date, Int>): List<Float> {

    val monthStars = dateGrouping
        .mapKeys { it.key.getMonthInt() }

    return (0..11).map {
        monthStars[it]?.toFloat() ?: 0f

    }
}

object MonthObj {
    val month = listOf(
        " Jan",
        " Feb",
        " Mar",
        " Apr",
        " May",
        " Jun",
        " Jul",
        " Aug",
        " Sep",
        " Oct",
        " Nov",
        " Dec"
    )
}

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

fun String.stringToUppearCase(): String {
    return this.trim().toUpperCase(Locale.US)}


