package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.classes.TokenManager
import com.example.myapplication.databinding.FragmentRegistrationSecondBinding
import com.example.myapplication.databinding.RegistrationPageBinding
import com.example.myapplication.retrofit.MainApi
import com.example.myapplication.retrofit.RetrofitInit
import com.example.myapplication.retrofit.SignUpDto
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.Locale


class RegistrationSecondFragment: Fragment(){

    private lateinit var binding: FragmentRegistrationSecondBinding
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var retrofitInit: RetrofitInit
    private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
        val registrationData = viewModel.registrationData.value
        retrofitInit = RetrofitInit()
        retrofitInit.initRetrofit("http://www.emilevi4.store/api/")
        tokenManager = TokenManager(requireContext())
        binding.apply {
            val backButton = registrationBackButton
            val registrationButton = registerBtn

            var username: String = ""
            var password: String = ""
            var email: String = ""

            if (registrationData != null){
                username = registrationData.username
                password = registrationData.password
                email = registrationData.email
            }

            val controller = findNavController()
            tvRegistrationError.visibility = View.VISIBLE

            backButton.setOnClickListener{
                controller.navigate(R.id.registrationPage)
            }

            registrationButton.setOnClickListener{

                registerUser(
                    username,
                    email,
                    password,
                    binding.fullNameField.text.toString(),
                    binding.birthDayField.text.toString()

                )
            }
        }
    }

    private fun registerUser(
        username: String,
        email: String,
        password: String,
        fullName: String,
        birthDay: String
    ){
        if (fullName.trim().isEmpty() || birthDay.trim().isEmpty()){
            binding.tvRegistrationError.visibility = View.VISIBLE
            binding.tvRegistrationError.text = "Please fill in all fields"
        }

        val dateFormatInput = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val date = dateFormatInput.parse(birthDay)

        val dateFormatOutput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormatOutput.format(date)

        val signUpDto: SignUpDto = SignUpDto(email, password, fullName, formattedDate, username)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitInit.mainApi.userRegistration(signUpDto)
            requireActivity().runOnUiThread{
                if (response.code() == 201){
                    val controller = findNavController()

                    val authResponse = response.body()
                    if (authResponse != null) {
                        tokenManager.saveTokens(authResponse.accessToken, authResponse.refreshToken)
                    }
                    controller.navigate(R.id.chatsPage)
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