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
import com.example.myapplication.adapter.ForumMessageAdapter
import com.example.myapplication.classes.Chat
import com.example.myapplication.classes.Forum
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.classes.UserModel
import com.example.myapplication.databinding.CorrespondencePageBinding
import com.example.myapplication.retrofit.RetrofitInit
import com.example.myapplication.singleton.ForumSocketHandler
import com.example.myapplication.singleton.SocketHandler
import io.socket.engineio.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class CorrespondenceFragment : Fragment() {
    private lateinit var binding: CorrespondencePageBinding
    private var forumId: String = ""
    private lateinit var retrofitInit: RetrofitInit
    private lateinit var tokenManager: TokenManager
    private var baseURL = BuildConfig.BASE_URL
    private var forum = Forum()
    private lateinit var forumMessageAdapter: ForumMessageAdapter
    private lateinit var userModel: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tokenManager = TokenManager(requireContext())
        ForumSocketHandler.setForumSocket(tokenManager.retrieveTokens().first.toString())
        ForumSocketHandler.establishConnection()
        binding = CorrespondencePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitInit = RetrofitInit()

        arguments?.let {
            forumId = it.getString("forumId", "");
            Log.i("Info", forumId)
        }

        var mSocket = ForumSocketHandler.getSocket()

        mSocket?.on("newForumMessage") {args ->
            try {
                Log.i("Socket", "newForumMessage triggered")
                val messageJson = args[0] as JSONObject
                Log.i("Socket", messageJson.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    val chat = Chat()
                    val messages =
                        forum.getForumInfo(tokenManager, retrofitInit, baseURL, forumId)?.messages
                    requireActivity().runOnUiThread {
                        if (messages != null) {
                            //messages?.get(1)?.let { Log.i("Messages", it.text) }
                            forumMessageAdapter.submitList(messages)
                            forumMessageAdapter.notifyDataSetChanged()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("Socket", "Listening newForumMessage exception: $e")
            }
        }

        initRcView()
        getForumInfo(tokenManager, retrofitInit, baseURL, binding, forumId)

        binding.apply {
            val controller = findNavController()
            correspondenceGoToChat.setOnClickListener {
                controller.navigate(R.id.chatsPage)
            }
            correspondenceGoToForums.setOnClickListener {
                controller.navigate(R.id.forumFragment)
            }

            correspondenceGoToOwnProfile.setOnClickListener {
                controller.navigate(R.id.ownProfilePage)
            }

            sendMessageBtn2.setOnClickListener {
                sendMessage(binding)
            }
        }

    }

    private fun getForumInfo(
        tokenManager: TokenManager,
        retrofitInit: RetrofitInit,
        baseURL: String,
        binding: CorrespondencePageBinding,
        forumId: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val getForumDto = forum.getForumInfo(tokenManager, retrofitInit, baseURL, forumId)
            requireActivity().runOnUiThread {
                binding.apply {
                    if (getForumDto != null) {
                        forumTitle.text = getForumDto.theme
                        correspondenceMemberCount.text =
                            getForumDto.memberships.count().toString() + " memberships"
                        forumMessageAdapter.submitList(getForumDto.messages)
                        forumMessageAdapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }

    private fun initRcView() = with(binding) {
        forumMessageAdapter = ForumMessageAdapter()
        forumMessageRecycleView.layoutManager = LinearLayoutManager(context)
        forumMessageRecycleView.adapter = forumMessageAdapter
    }

    private fun sendMessage(
        binding: CorrespondencePageBinding
    ) = with(binding) {
        val messageText = messageTextEdit2.text.toString()
        ForumSocketHandler.sendForumMessage(messageText, forumId)
        messageTextEdit2.setText("")

        getForumInfo(tokenManager, retrofitInit, baseURL, binding, forumId)
    }
}