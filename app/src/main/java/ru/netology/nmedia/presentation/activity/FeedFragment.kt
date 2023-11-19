package ru.netology.nmedia.presentation.activity

import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.R
import ru.netology.nmedia.ViewModel.PostViewModel
import ru.netology.nmedia.adapter.OnInterationListener
import ru.netology.nmedia.databinding.FragmentFeedBinding
import ru.netology.nmedia.dto.Post


class FeedFragment : Fragment (){
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )
        val viewModel: PostViewModel by activityViewModels()
        val adapter= PostAdapter(object: OnInterationListener {
            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun remove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun video(post: Post) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.videoURL.toString()))
                    startActivity(intent)

            }

            override fun edit(post: Post) {
                viewModel.edit(post)

            }
            override fun share(post: Post){
                viewModel.shareById(post.id)
                val intent=Intent().apply{
                    action=Intent.ACTION_SEND//можно выбирать из choose
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type="text/plain"
                    
                }
                //startActivity(intent) - куцый вариант
                //этот наполненней -
                val shareIntent=Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        }


        )
        binding.list.adapter=adapter
        viewModel.data.observe(viewLifecycleOwner){
            posts ->
            val newPost=posts.size > adapter.currentList.size
            adapter.submitList(posts){//по завершении потока - прокрутка
                if(newPost) {
                    binding.list.smoothScrollToPosition(0)
                }
            }
        }




        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }
        return binding.root
    }




    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

