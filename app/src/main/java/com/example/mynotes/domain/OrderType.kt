package com.example.mynotes.domain

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
