package pt.unbabel.android.demo.view.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import pt.unbabel.android.demo.R
import pt.unbabel.android.demo.databinding.FragmentPostsBinding
import pt.unbabel.android.demo.domain.Post
import pt.unbabel.android.demo.viewmodels.PostsViewModel

/**
 * A fragment to show the posts list, which are saved in a database.
 * Cumulative data is displayed in a RecyclerView.
 */
class PostsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentPostsBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_posts, container, false)

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = getViewModel()
        binding.postsViewModel = viewModel

        //RecyclerView Adapter for converting a list of Posts to cards.
        val adapter = PostAdapter(PostListener { postId -> viewModel.onPostClicked(postId) })
        binding.postsList.adapter = adapter

        viewModel.posts.observe(this, Observer { onPostsChanged(it, adapter) })

        viewModel.navigateToPostDetail.observe(this, Observer {
            it?.let { navigateToPostDetail(it, viewModel) }
        })

        return binding.root
    }

    private fun getViewModel(): PostsViewModel {
        val application = requireNotNull(this.activity).application

        return ViewModelProviders
            .of(this, PostsViewModel.Factory(application))
            .get(PostsViewModel::class.java)
    }

    private fun navigateToPostDetail(postId: Long, viewModel: PostsViewModel) {
        this.findNavController()
            .navigate(PostsFragmentDirections.actionPostsFragmentToDetailFragment(postId))
        /**
         * Reset state to make sure we only navigate once, even if the device
         * has a configuration change.
         */
        viewModel.onPostDetailNavigated()
    }

    private fun onPostsChanged(list: List<Post>, adapter: PostAdapter) {
        adapter.submitList(list)
    }
}