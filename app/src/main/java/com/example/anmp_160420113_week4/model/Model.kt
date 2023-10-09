package com.example.anmp_160420113_week4.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id: String?,
    @SerializedName("student_name") val fullName: String?,
    @SerializedName("birth_of_date") val dateOfBirth: String?,
    @SerializedName("phone") val phoneNumber: String?,
    @SerializedName("photo_url") val photoUrl: String?,
)