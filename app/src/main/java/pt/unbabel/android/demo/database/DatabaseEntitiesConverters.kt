package pt.unbabel.android.demo.database

import pt.unbabel.android.demo.domain.Comment
import pt.unbabel.android.demo.domain.Post
import pt.unbabel.android.demo.domain.PostDetail
import pt.unbabel.android.demo.domain.User

/**
 * Database entities converters to domain objects
 */
fun List<DatabasePost>.asDomainModel() = map { it.asDomainModel() }

fun DatabasePost.asDomainModel() = Post(id, postUserId, title, body)

fun DatabaseUser.asDomainModel() = User(userId, name, username, email, phone)

fun DatabaseComment.asDomainModel() = Comment(postId, commentId, name, email, body)

fun DatabasePostDetail.asDomainModel() = PostDetail(
        post = post.asDomainModel(),
        user = user.asDomainModel(),
        comments = comments.map { it.asDomainModel() }
    )