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
import com.example.myapplication.adapter.ChatMessageAdapter
import com.example.myapplication.classes.Chat
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.classes.UserModel
import com.example.myapplication.databinding.ChattingFragmentBinding
import com.example.myapplication.retrofit.ChatModel
import com.example.myapplication.retrofit.Message
import com.example.myapplication.retrofit.RetrofitInit
import com.example.myapplication.retrofit.SingleChatModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChattingFragment : Fragment() {

    private var chatId: String = ""
    private lateinit var binding: ChattingFragmentBinding
    private lateinit var retrofitInit: RetrofitInit
    private var baseURL: String = BuildConfig.BASE_URL
    private lateinit var tokenManager: TokenManager
    private lateinit var chatMessageAdapter: ChatMessageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ChattingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            chatId = it.getString("chatId", "");
            Log.i("Info", chatId)
        }
        retrofitInit = RetrofitInit()
        tokenManager = TokenManager(requireContext())
        chatMessageAdapter = ChatMessageAdapter()
        initRcView()
        binding.apply {
            var controller = findNavController()
            navToChatPageBtn.setOnClickListener{
                controller.navigate(R.id.chatsPage)
            }

            navToOwnProfilePageBtn.setOnClickListener{
                controller.navigate(R.id.ownProfilePage)
            }


            CoroutineScope(Dispatchers.IO).launch{
                val chatModel = getChatById()
                val chat = Chat()
                val messages = chat.getMessageOfChatById(retrofitInit, baseURL, tokenManager, chatId)
                requireActivity().runOnUiThread{
                    if (chatModel != null) {
                        messages?.get(1)?.let { Log.i("Messages", it.text) }
                        chatMessageAdapter.submitList(chatModel.messages)
                        chattingUsername.text = chatModel.chatMembers[0].user.nickname
                    }
                }
                val userModel: UserModel = UserModel()
                val user = userModel.getUserInfo(tokenManager, retrofitInit, baseURL)
                requireActivity().runOnUiThread {
                    if (user != null) {
                        chatMessageAdapter.setNickname(user.nickname)
                    }
                }
            }
        }

    }

    private suspend fun getChatById(): SingleChatModel?{
        var chat = Chat()
        return chat.getChatById(retrofitInit, baseURL, tokenManager, chatId)
    }

    private fun initRcView() = with(binding){
        chatMessageAdapter = ChatMessageAdapter()
        chattingRecycleView.layoutManager = LinearLayoutManager(context)
        chattingRecycleView.adapter = chatMessageAdapter
    }

    private fun updateAdapterList(newList: List<Message>) {
        chatMessageAdapter.submitList(newList)
    }

}