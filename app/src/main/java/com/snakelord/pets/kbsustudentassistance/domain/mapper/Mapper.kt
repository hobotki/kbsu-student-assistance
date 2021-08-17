package com.snakelord.pets.kbsustudentassistance.domain.mapper

interface Mapper<I, O> {
    fun map(input: I) : O
}