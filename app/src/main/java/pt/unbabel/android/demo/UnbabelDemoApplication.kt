package pt.unbabel.android.demo

import android.app.Application
import androidx.work.*
import pt.unbabel.android.demo.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Override application to setup background work via WorkManager
 */
class UnbabelDemoApplication : Application(){

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun delayedInit(){
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    /**
     * This function setups WorkManager
     */
    private fun setupRecurringWork() {

        /**
         * WorkRequest only runs if there is a network connection and if battery is not low
         */
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        /**
         * Setups [RefreshDataWorker] to run once a day
         */
        val repeatingRequest = PeriodicWorkRequest
            .Builder(RefreshDataWorker::class.java, 1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                RefreshDataWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
            )
    }

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }
}