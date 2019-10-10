package pt.unbabel.android.demo.view.detail

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
import pt.unbabel.android.demo.databinding.FragmentDetailBinding
import pt.unbabel.android.demo.viewmodels.PostDetailViewModel

/**
 * A fragment to show a post detail.
 */
class PostDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentDetailBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_detail, container, false)

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = this

        val viewModel = getViewModel()
        binding.viewModel = viewModel

        viewModel.navigateToPosts.observe(this, Observer { navigateToPosts(it, viewModel) })
        return binding.root
    }

    private fun getViewModel(): PostDetailViewModel {
        val application = requireNotNull(this.activity).application
        val postId = PostDetailFragmentArgs.fromBundle(arguments!!).postId

        return ViewModelProviders
            .of(this, PostDetailViewModel.Factory(postId, application))
            .get(PostDetailViewModel::class.java)
    }

    private fun navigateToPosts(navigate: Boolean?, viewModel: PostDetailViewModel){
        if(navigate == true){
            this.findNavController()
                .navigate(PostDetailFragmentDirections.actionDetailFragmentToPostsFragment())
            /**
             * Reset state to make sure we only navigate once, even if the device
             * has a configuration change.
             */
            viewModel.doneNavigating()
        }
    }
}