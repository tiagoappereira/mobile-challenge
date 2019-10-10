package pt.unbabel.android.demo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PostDao{

    @Query("SELECT * FROM DatabasePost")
    fun getPosts(): LiveData<List<DatabasePost>>

    @Query("SELECT * FROM DatabasePost INNER JOIN DatabaseUser ON DatabasePost.postUserId = DatabaseUser.userId WHERE id = :postId")
    fun getPostDetail(postId: Long) : LiveData<DatabasePostDetail>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg posts: DatabasePost)
}

@Dao
interface UserDao{

    @Query("SELECT * FROM DatabaseUser WHERE userId = :userId")
    fun getUser( userId: Long) : LiveData<DatabaseUser>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: DatabaseUser)
}

@Dao
interface CommentDao {

    @Query("SELECT * FROM DatabaseComment WHERE postId = :postId")
    fun getCommentsInPost(postId: Long) : LiveData<List<DatabaseComment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg comments: DatabaseComment)
}