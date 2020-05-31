package com.saurabh.kotlin_assignment.dataclass


import com.google.gson.annotations.SerializedName

data class PodMember(
    @SerializedName("member_id")
    val memberId: String, // t1
    @SerializedName("name")
    val name: String // Vrunda Vartak
)