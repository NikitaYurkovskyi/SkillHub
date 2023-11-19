package com.example.myapplication.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.classes.UserModel
import com.example.myapplication.databinding.FragmentOwnProfileEditingBinding
import com.example.myapplication.retrofit.RetrofitInit
import com.example.myapplication.retrofit.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class OwnProfileEditing : Fragment() {
    private lateinit var binding: FragmentOwnProfileEditingBinding
    private lateinit var tokenManager: TokenManager
    private lateinit var retrofitInit: RetrofitInit
    private var baseURL = "http://www.emilevi4.store/api/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOwnProfileEditingBinding.inflate(inflater, container, false)
        tokenManager = TokenManager(requireContext())
        retrofitInit = RetrofitInit()
        setUserField()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            var backButton = backBtn
            var controller = findNavController()


            backButton.setOnClickListener{
                controller.navigate(R.id.ownProfilePage)
            }

            changeNicknameBtn.setOnClickListener{
                nicknameField.isEnabled = true

                nicknameField.requestFocus()
                nicknameField.setSelection(nicknameField.text.length)
            }
            changeNameBtn.setOnClickListener{
                userProfileNameField.isEnabled = true
                userProfileNameField.requestFocus()
                userProfileNameField.setSelection(userProfileNameField.text.length)
            }
            changeBirthDateBtn.setOnClickListener{
                birthDateField.isEnabled = true
                birthDateField.requestFocus()
                birthDateField.setSelection(birthDateField.text.length)
            }
            changeEmailBtn.setOnClickListener{
                userProfileEmailField.isEnabled = true
                userProfileEmailField.requestFocus()
                userProfileEmailField.setSelection(userProfileEmailField.text.length)
            }

            applyChangesBtn.setOnClickListener{
                updateUser(
                    nicknameField.text.toString(),
                    userProfileNameField.text.toString(),
                    userProfileEmailField.text.toString(),
                    birthDateField.text.toString()
                )

            }
        }
    }

    private suspend fun getUserInfo(): User? {
        val userModel = UserModel()
        return userModel.getUserInfo(tokenManager, retrofitInit, baseURL)
    }


    private fun setUserField(){
        CoroutineScope(Dispatchers.IO).launch {
            val userInfo = getUserInfo()
            requireActivity().runOnUiThread(){
                binding.apply {
                    if (userInfo != null){
                        nicknameField.setText(userInfo.nickname)
                        userProfileNameField.setText(userInfo.name)
                        userProfileEmailField.setText(userInfo.email)
                        val birthDate = userInfo.birthDate
                        val sdf = SimpleDateFormat("yyyy.MM.dd")
                        val formattedDate = sdf.format(birthDate)
                        birthDateField.setText(formattedDate)

                    }
                }
            }
        }
    }

    private fun updateUser(
        newNickname: String,
        newName: String,
        newEmail: String,
        newBirthDate: String
        ){

        if (newName.trim().isEmpty() || newNickname.trim().isEmpty() || newEmail.trim().isEmpty() || newBirthDate.trim().isEmpty()){
            binding.tvProfileEditingError.text = "Please fill in all fields"
            binding.tvProfileEditingError.visibility = View.VISIBLE
            binding.nicknameField.isEnabled = false
            binding.userProfileNameField.isEnabled = false
            binding.userProfileEmailField.isEnabled = false
            binding.birthDateField.isEnabled = false
            binding.tvProfileEditingError.visibility = View.INVISIBLE
            return
        }
        val user = UserModel()
       CoroutineScope(Dispatchers.IO).launch {
           val newUserInfo = user.updateUser(
               newEmail,
               newName,
               newNickname,
               newBirthDate,
               tokenManager,
               retrofitInit,
               baseURL
           )
           requireActivity().runOnUiThread{
               if (newUserInfo != null) {
                   binding.nicknameField.setText(newUserInfo.nickname)
                   binding.userProfileNameField.setText(newUserInfo.name)
                   val birthDate = newUserInfo.birthDate
                   val sdf = SimpleDateFormat("yyyy.MM.dd")
                   val formattedDate = sdf.format(birthDate)
                   binding.birthDateField.setText(formattedDate)
                   binding.userProfileEmailField.setText(newUserInfo.email)

                   binding.nicknameField.isEnabled = false
                   binding.userProfileNameField.isEnabled = false
                   binding.userProfileEmailField.isEnabled = false
                   binding.birthDateField.isEnabled = false
                   binding.tvProfileEditingError.visibility = View.INVISIBLE
               } else {
                   binding.tvProfileEditingError.visibility = View.VISIBLE
                   binding.tvProfileEditingError.text = "A user with this data already exists"
                   binding.nicknameField.isEnabled = false
                   binding.userProfileNameField.isEnabled = false
                   binding.userProfileEmailField.isEnabled = false
                   binding.birthDateField.isEnabled = false
                   binding.tvProfileEditingError.visibility = View.INVISIBLE
               }
           }
       }


    }

}