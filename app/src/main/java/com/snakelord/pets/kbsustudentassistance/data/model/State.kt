package com.snakelord.pets.kbsustudentassistance.data.model

sealed class State {
    object Loading : State()
    data class Successful<T>(val result: T) : State()
    data class Error(val errorMessageId: Int) : State()
}