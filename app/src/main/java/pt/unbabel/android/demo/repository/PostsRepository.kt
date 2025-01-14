package pt.unbabel.android.demo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.unbabel.android.demo.database.PostsDatabase
import pt.unbabel.android.demo.database.asDomainModel
import pt.unbabel.android.demo.domain.Post
import pt.unbabel.android.demo.network.Network
import pt.unbabel.android.demo.network.asDatabaseModel

class PostsRepository(private val database: PostsDatabase){

    /**
     * A list of posts that can be shown on the screen
     */
    val posts: LiveData<List<Post>> = Transformations.map(database.postDao.getPosts()) {
        it.asDomainModel()
    }

    /**
     * Function that returns a PostDetail domain object given an id
     */
    fun getPost(postId: Long) = Transformations.map(database.postDao.getPostDetail(postId)){
        it.asDomainModel()
    }

    /**
     * Refresh the posts, users and comments stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert operation happens
     * on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * For now, this function is only called by WorkManager.
     */
    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO){
            val users = Network.service.getUsersAsync().await()
            database.userDao.insertAll(*users.asDatabaseModel())
            val posts = Network.service.getPostsAsync().await()
            database.postDao.insertAll(*posts.asDatabaseModel())
            val comments = Network.service.getCommentsAsync().await()
            database.commentDao.insertAll(*comments.asDatabaseModel())
        }
    }
}