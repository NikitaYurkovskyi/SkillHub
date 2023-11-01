package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.RegistrationPageBinding
import com.example.myapplication.retrofit.MainApi
import com.example.myapplication.retrofit.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationFragment : Fragment() {
    private lateinit var binding: RegistrationPageBinding
    private lateinit var mainApi: MainApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = RegistrationPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
        binding.apply {
            val goToAuthorization = linkToAuthorization
            val register = registerButton
            val controller = findNavController()

            goToAuthorization.setOnClickListener{
                controller.navigate(R.id.authorizationPage)
            }
            register.setOnClickListener{
                register(
                    UserDto(
                        emailField.text.toString(),
                        passwordField.text.toString(),
                        usernameField.text.toString()
                    )
                )
            }
        }
    }

    private fun initRetrofit(){
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.emilevi4.store/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        mainApi = retrofit.create(MainApi::class.java)
    }

    private fun register(registrationData: UserDto){
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainApi.userRegistration(registrationData)
            requireActivity().runOnUiThread{
                if (response.code() == 201){
                    val controller = findNavController()
                    controller.navigate(R.id.authorizationPage)
                }
                if (response.code() == 409){
                    binding.tvRegistrationError.visibility = View.VISIBLE
                    binding.tvRegistrationError.text = "User with such data already exist"
                }
                if (response.code() == 400){
                    binding.tvRegistrationError.visibility = View.VISIBLE
                    binding.tvRegistrationError.text = "Invalid registration data"
                }
            }
        }
    }
}