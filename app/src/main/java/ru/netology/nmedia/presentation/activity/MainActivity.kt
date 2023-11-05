package ru.netology.nmedia.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.ViewModel.PostViewModel
import ru.netology.nmedia.adapter.OnInterationListener
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.util.AndroidUtils.focusAndShowKeyboard


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter= PostAdapter(object: OnInterationListener {
            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }

            //override fun repost(post: Post) {
            //    viewModel.repostById(post.id)
            //}
            override fun remove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun edit(post: Post) {
                viewModel.edit(post)
            }
            override fun repost(post: Post){
                val intent=Intent().apply{
                    action=Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type="text/plain"
                }
                //startActivity(intent) - куцый вариант
                val shareIntent=Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }
        }


        )
        binding.recyclerList.adapter=adapter
        viewModel.data.observe(this){
            posts ->
            val newPost=posts.size > adapter.currentList.size
            adapter.submitList(posts){//по завершении потока - прокрутка
                if(newPost) {
                    binding.recyclerList.smoothScrollToPosition(0)
                }
            }
        }
        viewModel.edited.observe(this){
            if(it.id!=0L) {
                binding.content.setText(it.content)

                binding.content.focusAndShowKeyboard()
            }
        }
        val newPostLauncher= this.registerForActivityResult(NewPostResultContract){text:String ->
            text ?: return@registerForActivityResult
            viewModel.changeContentAndSave(text)
            viewModel.save()
        }
        binding.fab.setOnClickListener{
            newPostLaucher.launch()
        }
        binding.save.setOnClickListener{
            val text=binding.content.text.toString()

            if (text.isEmpty()){
                Toast.makeText(this@MainActivity, R.string.error_empty_content,Toast.LENGTH_LONG)
                return@setOnClickListener
            }
            viewModel.changeContentAndSave(text)


            binding.content.setText("")//сброс курсора
            binding.content.clearFocus()//сброс фокуса
            AndroidUtils.hideKeyboard(it)//сброс клавиатуры

        }

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

