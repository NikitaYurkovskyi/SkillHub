package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.classes.Forum
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.databinding.FragmentCreateForumBinding
import com.example.myapplication.retrofit.ForumDto
import com.example.myapplication.retrofit.RetrofitInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateForumFragment : Fragment() {
    private lateinit var binding: FragmentCreateForumBinding
    private lateinit var tokenManager: TokenManager
    private lateinit var retrofitInit: RetrofitInit
    private  var baseURL = BuildConfig.BASE_URL
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateForumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenManager = TokenManager(requireContext())
        retrofitInit = RetrofitInit()
        retrofitInit.initRetrofit(baseURL)

        val forum = Forum();

        binding.apply {
            val controller = findNavController()

            goToForumPage.setOnClickListener {
                controller.navigate(R.id.forumFragment)
            }

            goToChatPage.setOnClickListener {
                controller.navigate(R.id.chatsPage)
            }

            goToOwnProfilePage.setOnClickListener {
                controller.navigate(R.id.ownProfilePage)
            }

            createForumBtn.setOnClickListener {
                Log.i("Forum", "Clicked")
                handleCreateForumBtnClick(
                    createForumTitile.text.toString(),
                    createForumDescription.text.toString(),
                    forum,
                    binding
                )
            }
        }
    }

    private  fun handleCreateForumBtnClick(
        theme: String,
        description: String,
        forum: Forum,
        binding: FragmentCreateForumBinding
    ){
        CoroutineScope(Dispatchers.IO).launch {
            val response = forum.createForum(theme.trim(), description.trim(), tokenManager, retrofitInit, baseURL);

            requireActivity().runOnUiThread {
                if (response != null) {
                    binding.apply {
                        Log.i("Forum", response.toString())
                        if (response.code() == 201) {
                            val controller = findNavController()
                            controller.navigate(R.id.forumFragment)
                        } else {
                            forumCreateErrorString.text = "Invalid data"
                            forumCreateErrorString.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}