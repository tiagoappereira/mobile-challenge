package pt.unbabel.android.demo.view.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.unbabel.android.demo.databinding.ListItemPostBinding
import pt.unbabel.android.demo.domain.Post

class PostAdapter(private val clickListener: PostListener) :
    ListAdapter<Post, PostAdapter.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(private val binding: ListItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post, clickListener: PostListener) {
            binding.post = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPostBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}