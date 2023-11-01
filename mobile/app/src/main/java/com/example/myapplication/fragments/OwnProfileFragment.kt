package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class OwnProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.own_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var changeAccountButton = view.findViewById<Button>(R.id.changeAccountButton)
        var backButton = view.findViewById<Button>(R.id.backButton)
        var conroller = findNavController()

        changeAccountButton.setOnClickListener{
            conroller.navigate(R.id.authorizationPage)
        }

        backButton.setOnClickListener{
            conroller.navigate(R.id.chatsPage)
        }



    }
}