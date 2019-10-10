package pt.unbabel.android.demo.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import pt.unbabel.android.demo.R
import pt.unbabel.android.demo.domain.PostDetail

@BindingAdapter("postCommentsSizeFormatted")
fun TextView.setPostCommentsSizeFormatted(item: PostDetail?){
    item?.let {
        text = context.getString(R.string.post_comments_text, item.comments.size)
    }
}

@BindingAdapter("postAuthorFormatted")
fun TextView.setPostAuthorFormatted(item: PostDetail?){
    item?.let {
        text = context.getString(R.string.post_user_text, item.user.name)
    }
}