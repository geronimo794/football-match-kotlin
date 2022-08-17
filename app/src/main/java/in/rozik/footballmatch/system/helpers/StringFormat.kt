package `in`.rozik.footballmatch.system.helpers

import java.text.SimpleDateFormat
import java.util.*


// Tampilkan tanggal dengan hari
fun String.formatDateWithDay(): String {
    val fmtIn = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date = fmtIn.parse(this)
    val fmtOut = SimpleDateFormat("E, d MMM yyyy", Locale.US)
    return fmtOut.format(date)
}

// Tampilkan tanggal berdasarkan format GMT dan diubah jadi GMT Asia Jakarta
fun String.fullTimeFormatToDateWithDayGMT7(): String {
    val fmtIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.US)
    fmtIn.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
    return try {
        val date = fmtIn.parse(this)
        val fmtOut = SimpleDateFormat("E, d MMM yyyy", Locale.US)
        fmtOut.format(date)
    } catch (exc: Exception) {
        val getDate = this.split(" ")
        getDate[0]
    }
}

// Tampilkan jam berdasarkan format GMT dan diubah jadi GMT Asia Jakarta
fun String.fullTimeFormatToHourDayGMT7(): String {
    val fmtIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.US)
    fmtIn.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
    return try {
        val date = fmtIn.parse(this)
        val fmtOut = SimpleDateFormat("kk:mm", Locale.US)
        fmtOut.format(date) + " WIB"
    } catch (exc: Exception) {
        val getHour = this.split(" ")
        getHour[1]
    }
}

// Menghitung jarak usia sekarant hingga kini
fun String.getYearBetween(): String {
    val fmtIn = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val dateStart = fmtIn.parse(this)
    val timeStart = Calendar.getInstance()
    timeStart.time = dateStart
    val timeNow = Calendar.getInstance()

    var age = timeNow.get(Calendar.YEAR) - timeStart.get(Calendar.YEAR)

    if (timeNow.get(Calendar.DAY_OF_YEAR) < timeStart.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    return age.toString()
}

// Menampilkan tanggal sesuai format umum readable
fun String.getPublicDateFormat(): String {
    val fmtIn = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val dateIn = fmtIn.parse(this)
    val fmtOut = SimpleDateFormat("d MMMM yyyy", Locale.US)
    return fmtOut.format(dateIn)
}

/**
 * RIP Old Code
 *
 * */
//package `in`.rozik.footballmatch.system.helpers
//
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//// Tampilkan tanggal dengan hari
//fun String.formatDateWithDay(): String {
//    val fmtIn = SimpleDateFormat("yyyy-MM-dd", Locale.US)
//    val date = fmtIn.parse(this)
//    val fmtOut = SimpleDateFormat("E, d MMM yyyy", Locale.US)
//    return fmtOut.format(date)
//}
//
//// Tampilkan tanggal berdasarkan format GMT dan diubah jadi GMT Asia Jakarta
//fun String.fullTimeFormatToDateWithDayGMT7(): String {
//    var fmtIn: SimpleDateFormat
//    if (this.matches("\\d{2}:\\d{2}:\\d{2}\\+\\d{2}:\\d{2}".toRegex())) {
//        fmtIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH)
//    } else {
//        fmtIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
//    }
//    fmtIn.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
//    fmtIn.parse(this)
//    try {
//        val date = fmtIn.parse(this)
//        val fmtOut = SimpleDateFormat("E, d MMM yyyy", Locale.US)
//        return fmtOut.format(date)
//    } catch (exc: Exception) {
//        val getHour = this.split(" ")
//        return getHour[0]
//    }
//}
//
//// Tampilkan jam berdasarkan format GMT dan diubah jadi GMT Asia Jakarta
//fun String.fullTimeFormatToHourDayGMT7(): String {
//
//    var fmtIn: SimpleDateFormat
//    if (this.matches("\\d{2}:\\d{2}:\\d{2}\\+\\d{2}:\\d{2}".toRegex())) {
//        fmtIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.US)
//    } else {
//        fmtIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
//    }
//    fmtIn.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
//    try {
//        val date = fmtIn.parse(this)
//        val fmtOut = SimpleDateFormat("kk:mm", Locale.US)
//        return fmtOut.format(date) + " GMT+7"
//    } catch (exc: Exception) {
//        val getHour = this.split(" ")
//        return getHour[1]
//    }
//}
//
//// Menghitung jarak usia sekarant hingga kini
//fun String.getYearBetween(): String {
//    val fmtIn = SimpleDateFormat("yyyy-MM-dd", Locale.US)
//    val dateStart = fmtIn.parse(this)
//    val timeStart = Calendar.getInstance()
//    timeStart.time = dateStart
//    val timeNow = Calendar.getInstance()
//
//    var age = timeNow.get(Calendar.YEAR) - timeStart.get(Calendar.YEAR)
//
//    if (timeNow.get(Calendar.DAY_OF_YEAR) < timeStart.get(Calendar.DAY_OF_YEAR)) {
//        age--
//    }
//    return age.toString()
//}
//
//// Menampilkan tanggal sesuai format umum readable
//fun String.getPublicDateFormat(): String {
//    val fmtIn = SimpleDateFormat("yyyy-MM-dd", Locale.US)
//    val dateIn = fmtIn.parse(this)
//    val fmtOut = SimpleDateFormat("d MMMM yyyy", Locale.US)
//    return fmtOut.format(dateIn)
//}