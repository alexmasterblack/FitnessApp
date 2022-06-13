package com.example.fitnessapp.ui.login.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.retrofit.AuthHolder
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment(R.layout.fragment_login_page) {

    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authHolder = AuthHolder(requireContext())

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.showLoginError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputEditText>(R.id.login).error = "Введите логин"
            } else {
                view.findViewById<TextInputEditText>(R.id.login).error = null
            }
        }
        viewModel.showPasswordError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputEditText>(R.id.password).error = "Введите пароль"
            } else {
                view.findViewById<TextInputEditText>(R.id.password).error = null
            }
        }

        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            viewModel.onLoginClicked(
                view.findViewById<TextInputEditText>(R.id.login).text.toString(),
                view.findViewById<TextInputEditText>(R.id.password).text.toString()
            )
            viewModel.token.observe(viewLifecycleOwner) {
                authHolder.saveToken(it)
            }
            viewModel.result.observe(viewLifecycleOwner) {
                if (it == "Успех") {
                    findNavController().safeNavigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
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

    private fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run {
            navigate(direction)
        }
    }
}