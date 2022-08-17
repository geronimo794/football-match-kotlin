package `in`.rozik.footballmatch.system.helpers

import org.junit.Assert.assertEquals
import org.junit.Test

class StringFormatKtTest {

    @Test
    fun formatDateWithDay() {
        // Melakukan reformat tanggal dengan menampilkan hari
        val tanggalInput: String = "2019-01-01"
        assertEquals("Tue, 1 Jan 2019", tanggalInput.formatDateWithDay())
    }

    @Test
    fun fullTimeFormatToDateWithDayGMT7() {
        // Kalau di Indonesia ditambah 7 jam dan hari bertambah 1
        val tanggalInput: String = "2018-11-13 19:00:00+0000"
        assertEquals("Wed, 14 Nov 2018", tanggalInput.fullTimeFormatToDateWithDayGMT7())
    }

    @Test
    fun fullTimeFormatToHourDayGMT7() {
        // Karna GMT + 7 waktu ditambah 7 jam dan lihat jika melewati jam 24
        val tanggalInput: String = "2018-11-13 19:00:00+0000"
        assertEquals("02:00 WIB", tanggalInput.fullTimeFormatToHourDayGMT7())
    }

    @Test
    fun getYearBetween() {
        // Menghitung jarak usia
        val tanggalInput: String = "1999-12-31"
        assertEquals("18", tanggalInput.getYearBetween())
    }

    @Test
    fun getPublicDateFormat() {
        // Menampilkan tanggal sesuai tanggal readable
        val tanggalInput: String = "1999-09-01"
        assertEquals("1 September 1999", tanggalInput.getPublicDateFormat())
    }

}