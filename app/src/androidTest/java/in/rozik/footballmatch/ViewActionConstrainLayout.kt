

import android.support.constraint.ConstraintLayout
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.view.View
import android.widget.ImageView
import org.hamcrest.Matcher

object ViewActionConstrainLayout {

    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<ConstraintLayout>(id)
                v.performClick()
            }
        }
    }

}