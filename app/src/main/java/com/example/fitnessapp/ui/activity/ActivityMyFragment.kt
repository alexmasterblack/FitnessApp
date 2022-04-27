package com.example.fitnessapp.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.data.CardsData
import com.example.fitnessapp.domain.entity.CardType
import com.example.fitnessapp.ui.adapter.RecyclerViewAdapter


class ActivityMyFragment : Fragment(R.layout.fragment_activity_my) {

    private val activityData = CardsData()

    private val adapter = RecyclerViewAdapter {
        val bundle = Bundle()
        bundle.putString("Distance", it.distance)
        bundle.putString("Period", it.period)
        bundle.putString("TypeActivity", it.typeActivity)
        bundle.putString("DateActivity", it.dateActivity)
        bundle.putInt("CardType", it.cardType.ordinal)

        findNavController().navigate(
            R.id.action_activityFragment_to_activityDetailsFragment,
            bundle
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.myCards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ActivityMyFragment.adapter
        }
        adapter.setData(activityData.getDefaultData(CardType.MY))

    }
}