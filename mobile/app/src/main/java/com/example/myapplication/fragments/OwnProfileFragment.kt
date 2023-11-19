package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.classes.UserModel
import com.example.myapplication.databinding.OwnProfilePageBinding
import com.example.myapplication.retrofit.RetrofitInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OwnProfileFragment : Fragment() {
    private lateinit var binding: OwnProfilePageBinding
    private lateinit var tokenManager: TokenManager
    private lateinit var retrofitInit: RetrofitInit
    private var baseURL = "http://www.emilevi4.store/api/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = OwnProfilePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tokenManager = TokenManager(requireContext())
        retrofitInit = RetrofitInit()

        binding.apply {
            var changeAccountButton = changeAccountButton
            var backButton = backButton
            var editProfileButton = changeProfileBtn
            var conroller = findNavController()

            changeAccountButton.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    logOut()
                }
                conroller.navigate(R.id.authorizationPage)
            }

            backButton.setOnClickListener{
                conroller.navigate(R.id.chatsPage)
            }

            editProfileButton.setOnClickListener{
                conroller.navigate(R.id.ownProfileEditing2)
            }
        }
    }

    private suspend fun logOut(){
        val userModel = UserModel()
        userModel.logOut(tokenManager, retrofitInit, baseURL)
    }
}