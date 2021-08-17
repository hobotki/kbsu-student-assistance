package com.snakelord.pets.kbsustudentassistance.common.extensions

fun String.toJsonObject(): String {
    return substring(1, length - 1)
}

fun String.responseIsEmpty(): Boolean {
    return this == "[]"
}