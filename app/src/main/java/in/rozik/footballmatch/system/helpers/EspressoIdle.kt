package `in`.rozik.footballmatch.system.helpers

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource
import android.util.Log


object EspressoIdle {
    private val mCountingIdlingResource = CountingIdlingResource("GLOBAL")
    fun increment() {
        Log.d("EspressoIdle", "Increment")
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        Log.d("EspressoIdle", "Decrement")
        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource {
        return mCountingIdlingResource
    }
}