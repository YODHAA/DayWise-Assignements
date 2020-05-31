package com.saurabh.kotlin_assignment.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.saurabh.kotlin_assignment.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.recycler_main_screen.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_main_screen)

        val url1="https://jsonplaceholder.typicode.com/"
        val url2="https://attendence-call.s3.ap-south-1.amazonaws.com/"
        rv__list_posts.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url1).build()

        val retrofit2 = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(
            GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url2).build()

        val postsApi = retrofit.create(INetworkAPI::class.java)

        val postsApi2 = retrofit2.create(INetworkAPI::class.java)

        var response = postsApi.getAllPosts()

        val response2 = postsApi.getAllPods()

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
            rv__list_posts.adapter = PostItemAdapter(it, this)
        }

//        response2.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe {
//            rv__list_posts.adapter = PostItemAdapter(it, this)
//        }

    }
}