package com.devrachit.chocochipreader.Models

data class DetailsResponse(
    val name : String,
    val student_number : String,
    val branch : String,
    val is_hosteler : Boolean,
    val is_present : Boolean
)
