package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.RegistrationPageBinding
import com.example.myapplication.retrofit.MainApi
import com.example.myapplication.retrofit.SignUpDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationViewModel: ViewModel(){
    private val _registrationData = MutableLiveData<RegistrationData>()
    val registrationData: LiveData<RegistrationData> get() = _registrationData

    fun setRegistrationData(data: RegistrationData){
        _registrationData.value = data
    }
}
data class RegistrationData(val username: String, val email: String, val password: String)


class RegistrationFragment : Fragment() {
    private lateinit var binding: RegistrationPageBinding
    private lateinit var viewModel: RegistrationViewModel
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
        viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
        val registrationData = viewModel.registrationData.value

        //initRetrofit()
        binding.apply {
            val goToAuthorization = linkToAuthorization
            val registerNextBtn = registrationNextBtn
            val controller = findNavController()

            if (registrationData != null){
                usernameField.setText(registrationData.username)
                emailField.setText(registrationData.email)
                passwordField.setText(registrationData.password)
            }

            goToAuthorization.setOnClickListener{
                controller.navigate(R.id.authorizationPage)
            }
            registerNextBtn.setOnClickListener{
                goNextRegistrationPage()
            }
        }
    }

    private fun goNextRegistrationPage(){
        val username: String = binding.usernameField.text.toString()
        val email: String = binding.emailField.text.toString()
        val password: String = binding.passwordField.text.toString()

        if (username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()){
            binding.tvRegistrationError.visibility = View.VISIBLE
            binding.tvRegistrationError.text = "Please fill in all fields"
            return
        }
        val registrationData = RegistrationData(username, email, password)
        viewModel.setRegistrationData(registrationData)
        findNavController().navigate(R.id.registrationSecondFragment)
    }


}