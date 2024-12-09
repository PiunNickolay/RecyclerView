package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(
            onLikeListener = { post ->
                viewModel.likeById(post.id)
            },
            onShareListener = { post ->
                viewModel.shareById(post.id)

            }
        )
        binding.list?.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}
    fun countFormat(count: Int): String {
        if (count >= 1_000_000) {
            val millions = count / 1_000_000
            val remainder = (count % 1_000_000) / 100_000
            return if (remainder > 0) {
                "$millions.${remainder}M"
            } else {
                "$millions M"
            }
        } else if (count >= 10_000) {
            val thousands = count / 1_000
            return "$thousands K"
        } else if (count >= 1_100) {
            val thousands = count / 1_000
            val remainder = (count % 1_000) / 100
            return if (remainder > 0) {
                "$thousands.${remainder}K"
            } else {
                "$thousands K"
            }
        } else if (count >= 1_000) {
            return "${count / 1_000}K"
        } else {
            return count.toString()
        }
    }

