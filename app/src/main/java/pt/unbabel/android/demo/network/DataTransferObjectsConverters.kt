package pt.unbabel.android.demo.network

import pt.unbabel.android.demo.database.DatabaseComment
import pt.unbabel.android.demo.database.DatabasePost
import pt.unbabel.android.demo.database.DatabaseUser

/**
 * These are responsible for formatting network objects to send to the database
 */
fun List<NetworkPost>.asDatabaseModel(): Array<DatabasePost>{
    return map {
        DatabasePost(
            id = it.id,
            postUserId = it.userId,
            title = it.title,
            body = it.body
        )
    }.toTypedArray()
}

fun List<NetworkUser>.asDatabaseModel() : Array<DatabaseUser> {
    return map {
        DatabaseUser(
            userId = it.id,
            name = it.name,
            username = it.username,
            email = it.email,
            phone = it.phone
        )
    }.toTypedArray()
}

fun List<NetworkComment>.asDatabaseModel() : Array<DatabaseComment> {
    return map {
        DatabaseComment(
            postId = it.postId,
            commentId = it.id,
            name = it.name,
            email = it.email,
            body = it.body
        )
    }.toTypedArray()
}