package com.example.fitnessapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessapp.R
import com.example.fitnessapp.retrofit.AuthHolder
import com.example.fitnessapp.ui.CustomViewModelFactory
import com.example.fitnessapp.ui.login.registration.RegistrationViewModel
import com.example.fitnessapp.ui.main.WelcomeActivity
import com.google.android.material.textfield.TextInputEditText


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModels<ProfileViewModel> {
        CustomViewModelFactory { ProfileViewModel(AuthHolder(requireContext())) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.login.observe(viewLifecycleOwner) {
            view.findViewById<TextInputEditText>(R.id.login).setText(it)
        }

        viewModel.name.observe(viewLifecycleOwner) {
            view.findViewById<TextInputEditText>(R.id.name).setText(it)
        }

        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            viewModel.onLogoutClicked()
        }

        viewModel.result.observe(viewLifecycleOwner) {
            if (it == "Успех") {
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