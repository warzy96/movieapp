package fiveagency.internship.food.movieapp.ui.actordetails

import android.os.Handler
import android.os.Looper

private const val DEFAULT_DELAY = 5000L

class TimedAction(private val action: Runnable, private val actionDelay: Long = DEFAULT_DELAY) {
    private val handler: Handler = Handler(Looper.getMainLooper())

    fun start() {
        cancel()
        handler.postDelayed(action, actionDelay)
    }

    fun cancel() {
        handler.removeCallbacks(action)
    }
}