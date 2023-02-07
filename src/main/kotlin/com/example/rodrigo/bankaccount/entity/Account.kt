package com.example.rodrigo.bankaccount.entity

data class Account(
    val id: String? = null,
    val name: String,
    val document: String,
    val balance: Double? = 0.0,
    val active: Boolean? = true
)