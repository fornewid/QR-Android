package soup.qr

import android.content.Context
import timber.log.Timber

object LogTracker {

    fun install(context: Context) {
        Timber.plant(Timber.DebugTree())
    }
}
