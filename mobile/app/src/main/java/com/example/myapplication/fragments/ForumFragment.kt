package com.example.myapplication.fragments

import GetAllMyForumsAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.adapter.ChatAdapter
import com.example.myapplication.classes.Forum
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.databinding.FragmentForumBinding
import com.example.myapplication.retrofit.RetrofitInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ForumFragment : Fragment() {
    private lateinit var binding: FragmentForumBinding
    private lateinit var getAllMyForumsAdapter: GetAllMyForumsAdapter
    private lateinit var retrofitInit: RetrofitInit
    private var baseURL = BuildConfig.BASE_URL
    private lateinit var tokenManager: TokenManager
    private var forum = Forum()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitInit = RetrofitInit()
        tokenManager = TokenManager(requireContext())
        initRecycleView()
        submitAllMyForumsList(retrofitInit, baseURL, tokenManager)

        binding.apply {
            val controller = findNavController()

            goToChats.setOnClickListener {
                controller.navigate(R.id.chatsPage)
            }
            goToOwnProfile.setOnClickListener {
                controller.navigate(R.id.ownProfilePage)
            }

            goToCreateForum.setOnClickListener {
                controller.navigate(R.id.createForumFragment);
            }

            goToAllForumsFragment.setOnClickListener {
                controller.navigate(R.id.allForumsFragment);
            }
        }
    }

    private fun initRecycleView() = with(binding) {
        getAllMyForumsAdapter = GetAllMyForumsAdapter()
        rvAllForums.layoutManager = LinearLayoutManager(context)
        rvAllForums.adapter = getAllMyForumsAdapter

        getAllMyForumsAdapter.setOnForumClickListener { forumId ->
            val bundle = Bundle()
            bundle.putString("forumId", forumId)

            var controller = findNavController()
            controller.navigate(R.id.correspondenceFragment2, bundle)
        }

        getAllMyForumsAdapter.setOnLeaveForumClickListener {forumId ->
            CoroutineScope(Dispatchers.IO).launch {
                forum.leaveForum(tokenManager, retrofitInit, baseURL, forumId)
                requireActivity().runOnUiThread {
                    submitAllMyForumsList(retrofitInit, baseURL, tokenManager)
                }
            }
        }
    }

    private fun submitAllMyForumsList(
        retrofitInit: RetrofitInit,
        baseURL: String,
        tokenManager: TokenManager,
    ) = with(binding){
        CoroutineScope(Dispatchers.IO).launch {
            val getAllMyForumsDtoList = forum.getAllMyForums(tokenManager, retrofitInit, baseURL)
            requireActivity().runOnUiThread {
                if (!getAllMyForumsDtoList.isNullOrEmpty()) {
                    getAllMyForumsAdapter.submitList(getAllMyForumsDtoList)
                }
            }
        }
    }
}