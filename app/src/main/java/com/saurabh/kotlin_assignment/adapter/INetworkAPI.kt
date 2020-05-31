package com.saurabh.kotlin_assignment.adapter

import com.saurabh.kotlin_assignment.dataclass.Pod
import com.saurabh.kotlin_assignment.dataclass.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface INetworkAPI {

    @GET("posts/")
    fun getAllPosts(): Observable<List<Post>>

    @GET("ApiCall/employee_call.json")
    fun getAllPods():Observable<List<Pod>>
}