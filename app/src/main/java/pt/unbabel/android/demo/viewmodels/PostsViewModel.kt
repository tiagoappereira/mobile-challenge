package pt.unbabel.android.demo.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.unbabel.android.demo.database.PostsDatabase
import pt.unbabel.android.demo.repository.PostsRepository

/**
 * The ViewModel that is attached to the PostsFragment, containing the Posts list.
 */
class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val database = PostsDatabase.getDatabase(application)
    private val repo = PostsRepository(database)

    val posts = repo.posts

    /**
     * Variable that tells the fragment whether it should navigate to PostDetailFragment.
     * This is `private` because we don't want to expose the ability to set [MutableLiveData] to
     * the Fragment
     */
    private val _navigateToPostDetail = MutableLiveData<Long>()
    // External immutable LiveData for the navigation to a selected post
    val navigateToPostDetail
        get() = _navigateToPostDetail

    /**
     * When the post is clicked, set the [_navigateToPostDetail] [MutableLiveData]
     * @param id The id of the post that was clicked on.
     */
    fun onPostClicked(id: Long) {
        _navigateToPostDetail.value = id
    }

    /**
     * Call this immediately after navigating to PostDetailFragment
     */
    fun onPostDetailNavigated() {
        _navigateToPostDetail.value = null
    }

    /**
     * Factory for constructing PostsViewModel with parameters
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