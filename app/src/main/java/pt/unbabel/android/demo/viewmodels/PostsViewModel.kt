package pt.unbabel.android.demo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.unbabel.android.demo.database.getDatabase
import pt.unbabel.android.demo.repository.PostsRepository

/**
 * PostsViewModel designed to store and manage UI-related data in a lifecycle conscious way.
 * Allows data to survive configuration changes such as screen rotations.
 *
 * @param application The application that this ViewModel is attached to, it's safe to hold a
 * reference to applications across rotation since Application is never recreated during activity
 * or fragment lifecycle events.
 */
class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repo = PostsRepository(database)

    val posts = repo.posts

    private val _navigateToPostDetail = MutableLiveData<Long>()
    val navigateToPostDetail
        get() = _navigateToPostDetail

    fun onPostClicked(id: Long) {
        _navigateToPostDetail.value = id
    }

    fun onPostDetailNavigated() {
        _navigateToPostDetail.value = null
    }

    /**
     * Factory for constructing PostsViewModel with parameter
     */
    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PostsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}