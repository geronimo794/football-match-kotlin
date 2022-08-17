package `in`.rozik.footballmatch

import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL


class ApiRepository {
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
//         Thread.sleep(2000)
        val response = URL(url).readText()
        Log.d("ApiRepository.response", response)
        Log.d("ApiRepository.url", url)
        response
    }

}