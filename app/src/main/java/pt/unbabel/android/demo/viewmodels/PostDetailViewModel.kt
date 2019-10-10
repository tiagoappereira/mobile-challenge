package pt.unbabel.android.demo.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.unbabel.android.demo.database.PostsDatabase
import pt.unbabel.android.demo.domain.PostDetail
import pt.unbabel.android.demo.repository.PostsRepository

/**
 *  The ViewModel associated with the PostDetailFragment, containing information about the selected
 *  Post.
 */
class PostDetailViewModel(postId: Long, application: Application): ViewModel() {

    private val database = PostsDatabase.getDatabase(application)
    private val repo = PostsRepository(database)

    private val _selectedPost = repo.getPost(postId)

    //The external LiveData for the selected Post
    val selectedPost : LiveData<PostDetail>
        get() = _selectedPost

    /**
     * Variable that tells the fragment whether it should navigate to PostsFragment.
     * This is `private` because we don't want to expose the ability to set [MutableLiveData] to
     * the Fragment
     */
    private val _navigateToPosts = MutableLiveData<Boolean?>()

    /**
    * When true immediately navigate back to the PostsFragment
    */
    val navigateToPosts: LiveData<Boolean?>
        get() = _navigateToPosts

    /**
     * Call this immediately after navigating to PostsFragment
     */
    fun doneNavigating() {
        _navigateToPosts.value = null
    }

    fun onClose() {
        _navigateToPosts.value = true
    }

    /**
     * Factory for constructing PostDetailViewModel with parameters
     */
    class Factory(private val postKey: Long, private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostDetailViewModel::class.java)) {
                return PostDetailViewModel(postKey, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}