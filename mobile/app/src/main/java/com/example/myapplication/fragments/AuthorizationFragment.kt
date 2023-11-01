package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.LoginViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.AuthorizationPageBinding
import com.example.myapplication.retrofit.MainApi
import com.example.myapplication.retrofit.UserAuthForm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthorizationFragment : Fragment() {
    private lateinit var binding: AuthorizationPageBinding
    private lateinit var mainApi: MainApi
    private val viewModel: LoginViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AuthorizationPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            initRetrofit()
            val goToRegistration = goToRegistration
            val authorize = authorizedButton
            val controller = findNavController()

            goToRegistration.setOnClickListener{
                controller.navigate(R.id.registrationPage)
            }

            authorize.setOnClickListener{
                authorization(
                    UserAuthForm(
                        loginField.text.toString(),
                        passwordField.text.toString()
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

    private fun authorization(userAuthForm: UserAuthForm){
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainApi.userLogin(userAuthForm)
            requireActivity().runOnUiThread{
                if (response.code() == 401){
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.text = "Invalid email or password"
                } else if (response.code() == 400) {
                    binding.tvError.visibility = View.VISIBLE
                    binding.tvError.text = "Invalid email or password"
                }
                val authResponse = response.body()
                if (authResponse != null){
                    viewModel.token.value = authResponse.accessToken
                    val controller = findNavController()
                    controller.navigate(R.id.chatsPage)
                }
            }
        }
    }
}