package pt.unbabel.android.demo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * A database that caches [DatabasePost], [DatabaseUser] and [DatabaseComment]
 */
@Database(entities = [DatabasePost::class, DatabaseUser::class, DatabaseComment::class], version = 1)
abstract class PostsDatabase : RoomDatabase(){
    abstract val postDao: PostDao
    abstract val userDao: UserDao
    abstract val commentDao: CommentDao

    /**
     * Call `PostsDatabase.getInstance(context)` to instantiate a new PostsDatabase.
     */
    companion object{
        private const val DATABASE_NAME = "posts"

        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         * This will help avoid repeatedly initializing the database, which is expensive.
         */
        private lateinit var INSTANCE: PostsDatabase

        fun getDatabase(context: Context) : PostsDatabase {
            synchronized(PostsDatabase::class.java){
                if(!::INSTANCE.isInitialized){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PostsDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }

}