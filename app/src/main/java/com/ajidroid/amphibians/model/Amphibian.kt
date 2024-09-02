package com.ajidroid.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val name : String,
    val type : String,
    @SerialName(value = "img_src") val image : String,
    val description : String
)
//object FakeData{
//    val fakelist = listOf(
//        Amphibian(
//            "name1",
//            "image1",
//            "description1"
//        ),
//        Amphibian(
//            "name2",
//            "image2",
//            "description2"
//        ),
//        Amphibian(
//            "name3",
//            "image3",
//            "description3"
//        )
//    )
//}