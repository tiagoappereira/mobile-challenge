package pt.unbabel.android.demo.database

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * These are objects representing database entities.
 */
@Entity(foreignKeys = [ForeignKey(entity = DatabaseUser::class, parentColumns = ["userId"],
    childColumns = ["postUserId"], onDelete = CASCADE)])
data class DatabasePost(
    @PrimaryKey
    val id: Long,
    val postUserId: Long,
    val title: String,
    val body: String
)

@Entity
data class DatabaseUser(
    @PrimaryKey
    val userId: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String
)

@Entity(primaryKeys = ["postId", "commentId"], foreignKeys = [ForeignKey(
    entity = DatabasePost::class, parentColumns = ["id"], childColumns = ["postId"],
    onDelete = CASCADE)])
data class DatabaseComment(
    val postId: Long,
    val commentId: Long,
    val name: String,
    val email: String,
    val body: String
)

data class DatabasePostDetail(
    @Embedded
    val post: DatabasePost,
    @Embedded
    val user: DatabaseUser,
    @Relation(parentColumn = "id", entityColumn = "postId")
    val comments: List<DatabaseComment>
)