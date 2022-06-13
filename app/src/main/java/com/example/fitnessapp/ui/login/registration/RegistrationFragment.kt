package com.example.fitnessapp.ui.login.registration

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.retrofit.AuthHolder
import com.google.android.material.textfield.TextInputEditText


class RegistrationFragment : Fragment(R.layout.fragment_registration_page) {

    private val viewModel by lazy { ViewModelProvider(this)[RegistrationViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val span =
            SpannableString("Нажимая на кнопку, вы соглашаетесь с политикой конфиденциальности и обработки персональных данных, а также принимаете пользовательское соглашение")
        val clickSpanOne: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }

            override fun onClick(view: View) {
                Toast.makeText(
                    requireActivity().application,
                    "Политика конфиденциальности",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        val clickSpanTwo: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(text: TextPaint) {
                text.color = text.linkColor
                text.isUnderlineText = false
            }

            override fun onClick(view: View) {
                Toast.makeText(
                    requireActivity().application,
                    "Пользовательское соглашение",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        span.setSpan(clickSpanOne, 37, 66, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(clickSpanTwo, span.length - 27, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val text = view.findViewById<TextView>(R.id.agreementView)
        text.movementMethod = LinkMovementMethod()
        text.text = span

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val authHolder = AuthHolder(requireContext())

        viewModel.showLoginError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputEditText>(R.id.login).error = "Введите логин"
            } else {
                view.findViewById<TextInputEditText>(R.id.login).error = null
            }
        }
        viewModel.showNameError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputEditText>(R.id.name).error = "Введите имя или никнейм"
            } else {
                view.findViewById<TextInputEditText>(R.id.name).error = null
            }
        }
        viewModel.showPasswordError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputEditText>(R.id.password).error = "Введите пароль"
            } else {
                view.findViewById<TextInputEditText>(R.id.password).error = null
            }
        }
        viewModel.showRepeatPasswordError.observe(viewLifecycleOwner) {
            if (it) {
                view.findViewById<TextInputEditText>(R.id.passwordRepeat).error = "Повторите пароль"
            } else {
                view.findViewById<TextInputEditText>(R.id.passwordRepeat).error = null
            }
        }

        var gender = 0
        view.findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.man) {
                gender = 0
            } else if (checkedId == R.id.woman) {
                gender = 1
            }
        }

        view.findViewById<Button>(R.id.btnRegistration).setOnClickListener {
            viewModel.onRegistrationClicked(
                view.findViewById<TextInputEditText>(R.id.login).text.toString(),
                view.findViewById<TextInputEditText>(R.id.password).text.toString(),
                view.findViewById<TextInputEditText>(R.id.passwordRepeat).text.toString(),
                view.findViewById<TextInputEditText>(R.id.name).text.toString(),
                gender
            )
            viewModel.token.observe(viewLifecycleOwner) {
                authHolder.saveToken(it)
            }
            viewModel.result.observe(viewLifecycleOwner) {
                if (it == "Успех") {
                    findNavController().safeNavigate(RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment())
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