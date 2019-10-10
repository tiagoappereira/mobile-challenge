package pt.unbabel.android.demo.view.posts

import pt.unbabel.android.demo.domain.Post

class PostListener(val clickListener: (postId: Long) -> Unit) {
    fun onClick(post: Post) = clickListener(post.id)
}