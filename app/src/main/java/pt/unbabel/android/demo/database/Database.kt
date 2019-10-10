package pt.unbabel.android.demo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "posts"

@Database(entities = [DatabasePost::class, DatabaseUser::class, DatabaseComment::class], version = 1)
abstract class PostsDatabase : RoomDatabase(){
    abstract val postDao: PostDao
    abstract val userDao: UserDao
    abstract val commentDao: CommentDao
}

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