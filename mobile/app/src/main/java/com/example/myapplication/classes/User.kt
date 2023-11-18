package com.example.myapplication.classes

import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.retrofit.MainApi
import com.example.myapplication.retrofit.SignUpDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class User {

//    private fun register(registrationData: SignUpDto){
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = mainApi.userRegistration(registrationData)
//            requireActivity().runOnUiThread{
//                if (response.code() == 201){
//                    val controller = findNavController()
//                    controller.navigate(R.id.authorizationPage)
//                }
//                if (response.code() == 409){
//                    binding.tvRegistrationError.visibility = View.VISIBLE
//                    binding.tvRegistrationError.text = "User with such data already exist"
//                }
//                if (response.code() == 400){
//                    binding.tvRegistrationError.visibility = View.VISIBLE
//                    binding.tvRegistrationError.text = "Invalid registration data"
//                }
//            }
//        }
//    }

}