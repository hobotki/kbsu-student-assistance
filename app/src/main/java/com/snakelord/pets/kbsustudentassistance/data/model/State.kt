package com.snakelord.pets.kbsustudentassistance.data.model

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Successful<out T>(val result: T) : State<T>()
    data class Error(val errorMessageId: Int) : State<Nothing>()
}