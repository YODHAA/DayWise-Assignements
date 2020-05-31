package com.saurabh.kotlin_assignment.dataclass


import com.google.gson.annotations.SerializedName

data class Pod(
    @SerializedName("more-details")
    val moreDetails: List<Any>,
    @SerializedName("pod_match")
    val podMatch: List<PodMatch>,
    @SerializedName("pod_members")
    val podMembers: List<PodMember>,
    @SerializedName("status")
    val status: Int // 1
)