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
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.classes.UserModel
import com.example.myapplication.databinding.ChatsPageBinding
import com.example.myapplication.retrofit.OtherUserProfile
import com.example.myapplication.retrofit.RetrofitInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatsFragment : Fragment() {
    private lateinit var binding: ChatsPageBinding
    private lateinit var adapter: UserAdapter
    private lateinit var retrofitInit: RetrofitInit
    private var baseURL = BuildConfig.BASE_URL

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
        initRcView()
        retrofitInit = RetrofitInit()
        CoroutineScope(Dispatchers.IO).launch {
            val randomUsersProfile = getRandomUsers()
            requireActivity().runOnUiThread {
                Log.i("ChatsFragment", randomUsersProfile.toString())
                adapter.submitList(randomUsersProfile)

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

    private suspend fun getRandomUsers(): List<OtherUserProfile>? {
        val user = UserModel()
        return user.getAllUser(retrofitInit, baseURL)
    }

    private fun updateAdapterList(newList: List<OtherUserProfile>) {
        adapter.submitList(newList)
    }

    private fun initRcView() = with(binding){
        adapter = UserAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}