package pt.unbabel.android.demo.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import pt.unbabel.android.demo.database.PostsDatabase
import pt.unbabel.android.demo.repository.PostsRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object{
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to background work.
     */
    override suspend fun doWork(): Result {
        val database = PostsDatabase.getDatabase(applicationContext)
        val repository = PostsRepository(database)
        return try {
            repository.refreshDatabase()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}