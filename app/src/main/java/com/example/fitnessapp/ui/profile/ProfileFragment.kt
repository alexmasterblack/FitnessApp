package com.example.fitnessapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessapp.R
import com.example.fitnessapp.retrofit.AuthHolder
import com.example.fitnessapp.ui.main.WelcomeActivity
import com.google.android.material.textfield.TextInputEditText


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by lazy { ViewModelProvider(this)[ProfileViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authHolder = AuthHolder(requireActivity())

        viewModel.setProfile("Bearer ".plus(authHolder.getToken()))

        viewModel.login.observe(viewLifecycleOwner) {
            view.findViewById<TextInputEditText>(R.id.login).setText(it)
        }

        viewModel.name.observe(viewLifecycleOwner) {
            view.findViewById<TextInputEditText>(R.id.name).setText(it)
        }

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            viewModel.onLogoutClicked("Bearer ".plus(authHolder.getToken()))

            viewModel.result.observe(viewLifecycleOwner) {
                if (it == "Успех") {
                    authHolder.cleanToken()
                    startActivity(Intent(activity, WelcomeActivity::class.java))
                    activity?.finish()
                } else if (it != "") {
                    Toast.makeText(
                        requireActivity().application,
                        it,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}