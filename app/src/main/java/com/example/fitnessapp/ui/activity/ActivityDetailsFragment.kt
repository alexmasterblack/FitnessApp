package com.example.fitnessapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.domain.entity.CardType
import com.google.android.material.textfield.TextInputLayout


class ActivityDetailsFragment : Fragment(R.layout.fragment_activity_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeCardActivity = CardType.values()[arguments?.getInt("CardType")!!]

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = arguments?.getString("TypeActivity")

        view.findViewById<TextView>(R.id.distance).text = arguments?.getString("Distance")
        view.findViewById<TextView>(R.id.dateActivity).text = arguments?.getString("DateActivity")
        view.findViewById<TextView>(R.id.period).text = arguments?.getString("Period")

        val nickname = view.findViewById<TextView>(R.id.nickname)
        nickname.text = arguments?.getString("Nickname")
        nickname.visibility = View.GONE;

        if (typeCardActivity == CardType.USERS) {
            val comment = view.findViewById<TextInputLayout>(R.id.comment)
            comment.isEnabled = false
            comment.isFocusable = false

            toolbar.menu.findItem(R.id.delete).isVisible = false
            toolbar.menu.findItem(R.id.share).isVisible = false
            nickname.visibility = View.VISIBLE;
        }

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}