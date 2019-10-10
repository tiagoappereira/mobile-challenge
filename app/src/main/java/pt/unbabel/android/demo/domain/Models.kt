package pt.unbabel.android.demo.domain

/**
 * These are domain objects that user can directly interact with.
 */
data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)

data class PostDetail(
    val post: Post,
    val user: User,
    val comments: List<Comment>
)

data class User(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String
)

data class Comment(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)