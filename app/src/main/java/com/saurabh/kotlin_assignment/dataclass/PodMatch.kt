package com.saurabh.kotlin_assignment.dataclass


import com.google.gson.annotations.SerializedName

data class PodMatch(
    @SerializedName("member_id")
    val memberId: String, // t1
    @SerializedName("slot_id")
    val slotId: String // 1
)