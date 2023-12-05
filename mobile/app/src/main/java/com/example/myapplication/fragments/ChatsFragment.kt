package com.example.myapplication.fragments

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
import com.example.myapplication.classes.Chat
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.classes.UserModel
import com.example.myapplication.databinding.ChatsPageBinding
import com.example.myapplication.retrofit.ChatModel
import com.example.myapplication.retrofit.RetrofitInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatsFragment : Fragment() {
    private lateinit var binding: ChatsPageBinding
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var retrofitInit: RetrofitInit
    private var baseURL = BuildConfig.BASE_URL
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = ChatsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitInit = RetrofitInit()
        tokenManager = TokenManager(requireContext())

        initRcView()

        CoroutineScope(Dispatchers.IO).launch {
            val userModel: UserModel = UserModel()
            val user = userModel.getUserInfo(tokenManager, retrofitInit, baseURL)
            requireActivity().runOnUiThread {
                if (user != null) {
                    chatAdapter.setNickname(user.nickname)
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val userChats = getUserChats()
            requireActivity().runOnUiThread {
                Log.i("ChatsFragment", userChats.toString())
                chatAdapter.submitList(userChats)
            }
        }

        binding.apply {
            var ownProfileButton = viewOwnProfile
            var goToForumButton = goToForumBtn
            var controller = findNavController()

            ownProfileButton.setOnClickListener{
                controller.navigate(R.id.ownProfilePage)
            }

            goToForumButton.setOnClickListener{
                controller.navigate(R.id.correspondenceFragment2)
            }

        }
    }

    private suspend fun getUserChats(): List<ChatModel>? {
        val chat = Chat()
        return chat.getAllUserChat(retrofitInit, baseURL, tokenManager)
    }

    private fun updateAdapterList(newList: List<ChatModel>) {
        chatAdapter.submitList(newList)
    }

    private fun initRcView() = with(binding){
        chatAdapter = ChatAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = chatAdapter

        chatAdapter.setOnChatClickListener { chatId ->
            val bundle = Bundle()
            bundle.putString("chatId", chatId)

            var controller = findNavController()
            controller.navigate(R.id.chattingFragment, bundle)
        }
    }
}