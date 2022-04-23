package com.example.fitnessapp.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.data.CardsData
import com.example.fitnessapp.domain.entity.CardType
import com.example.fitnessapp.ui.adapter.RecyclerViewAdapter


class ActivityUsersFragment : Fragment(R.layout.fragment_activity_users) {

    private val activityData = CardsData()

    private val adapter = RecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.usersCards).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ActivityUsersFragment.adapter
        }
        adapter.setData(activityData.getDefaultData(CardType.USERS))
    }

}