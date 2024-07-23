package com.example.anmp_160420113_week4.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id: String?,
    @SerializedName("student_name") val fullName: String?,
    @SerializedName("birth_of_date") val dateOfBirth: String?,
    @SerializedName("phone") val phoneNumber: String?,
    @SerializedName("photo_url") val photoUrl: String?,
) : Parcelable

data class Car(
    val model:String?,
    val year:String?,
    val features:List<String>?,
    val specs:CarSpecification?,
)
data class CarSpecification(
    val engine:String?,
    val transmission:String?,
    val fuelType:String?,
)
