package com.saurabh.kotlin_assignment.dataclass


import com.google.gson.annotations.SerializedName

//class Post : List<PostItem>()
data class Post(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)