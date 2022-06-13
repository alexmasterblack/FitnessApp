package com.example.fitnessapp.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.retrofit.AuthHolder
import com.example.fitnessapp.ui.login.WelcomeFragment
import com.google.android.material.button.MaterialButton
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

        view.findViewById<MaterialButton>(R.id.btnLogout).setOnClickListener {
            viewModel.onLogoutClicked("Bearer ".plus(authHolder.getToken()))
            viewModel.result.observe(viewLifecycleOwner) {
                if (it == "Успех") {
                    val fragmentManager = requireActivity().supportFragmentManager

                    val welcome = WelcomeFragment()

                    fragmentManager.beginTransaction()
                        .add(R.id.containerView, welcome)
                        .addToBackStack(null)
                        .commit()

                    welcome.findNavController().setGraph(R.navigation.navigation_graph)

                    fragmentManager.beginTransaction().replace(R.id.containerView, welcome).commit()

                } else {
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