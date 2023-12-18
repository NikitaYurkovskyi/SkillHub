package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.adapter.AllForumsAdapter
import com.example.myapplication.adapter.ChatAdapter
import com.example.myapplication.classes.Forum
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.databinding.FragmentAllForumsBinding
import com.example.myapplication.retrofit.RetrofitInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AllForumsFragment : Fragment() {
    private lateinit var binding: FragmentAllForumsBinding
    private lateinit var allForumsAdapter: AllForumsAdapter
    private lateinit var retrofitInit: RetrofitInit
    private var baseURL = BuildConfig.BASE_URL
    private lateinit var tokenManager: TokenManager
    private var forum = Forum()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAllForumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitInit = RetrofitInit()
        tokenManager = TokenManager(requireContext())
        initGetAllForumsRecycleView()
        submitAllForumsList(retrofitInit, baseURL, tokenManager)

        binding.apply {
            val controller = findNavController()

            goToChats.setOnClickListener {
                controller.navigate(R.id.chatsPage)
            }

            goToOwnProfile.setOnClickListener {
                controller.navigate(R.id.ownProfilePage)
            }

            goToForumBtn3.setOnClickListener {
                controller.navigate(R.id.forumFragment)
            }
        }
    }

    private fun initGetAllForumsRecycleView() = with(binding) {
        allForumsAdapter = AllForumsAdapter()
        rvAllForums.layoutManager = LinearLayoutManager(context)
        rvAllForums.adapter = allForumsAdapter

        allForumsAdapter.setOnChatClickListener { forumId ->
           CoroutineScope(Dispatchers.IO).launch {
               forum.joinForum(tokenManager, retrofitInit, baseURL, forumId)
               val bundle = Bundle()
               bundle.putString("forumId", forumId)
           }
        }

    }

    private fun submitAllForumsList(
        retrofitInit: RetrofitInit,
        baseURL: String,
        tokenManager: TokenManager,
    ) = with(binding){
        CoroutineScope(Dispatchers.IO).launch {
            val getAllForumDtoList = forum.getAllForums(tokenManager, retrofitInit, baseURL)
            requireActivity().runOnUiThread {
                if (!getAllForumDtoList.isNullOrEmpty()) {
                    allForumsAdapter.submitList(getAllForumDtoList)
                }
            }
        }
    }


}