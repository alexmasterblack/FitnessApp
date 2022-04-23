package com.example.fitnessapp.domain.entity

sealed class ListItem(val id: Int) {
    class SplitDate(id: Int, val date: String) : ListItem(id)
    class CardActivity(
        id: Int,
        val distance: String,
        val period: String,
        val typeActivity: String,
        val dateActivity: String,
        val cardType: CardType,
        val nickname: String = ""
    ) : ListItem(id)
}