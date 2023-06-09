package com.example.middleexam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.middleexam.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ViewModel :ViewModel(){

    private val topStoryLifeData by lazy {
        MutableLiveData<List<TopStory>>()
    }
    private val storyLIfeData by lazy {
        MutableLiveData<List<Story>>()
    }

    private val assessLIfeData by lazy {
        MutableLiveData<List<Comment>>()
    }
    fun get_topStoryLifeData(): MutableLiveData<List<TopStory>>{
        return topStoryLifeData
    }

    fun get_assessLIfeData():MutableLiveData<List<Comment>>{
        return assessLIfeData
    }
    fun get_StoryLIfeData():MutableLiveData<List<Story>>{
        return storyLIfeData
    }
    fun getDataInFragment(){
        val retrofit=Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val testService=retrofit.create(FirstService::class.java)
        testService.getInternetData1().enqueue(object :Callback<FirstData>{
            override fun onResponse(call: Call<FirstData>, response: Response<FirstData>) {
                val data=response.body()
                if (data!=null){
                    topStoryLifeData.postValue(data.top_stories)
                }
            }
            override fun onFailure(call: Call<FirstData>, t: Throwable) {
            }
        }
        )
    }
    fun getDataInMain1(){
        val retrofit=Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val testService=retrofit.create(FirstService::class.java)
        testService.getInternetData1().enqueue(object :Callback<FirstData>{
            override fun onResponse(call: Call<FirstData>, response: Response<FirstData>) {
                val data=response.body()
                if (data!=null){
                    storyLIfeData.postValue(data.stories)
                }
            }
            override fun onFailure(call: Call<FirstData>, t: Throwable) {
            }
        }
        )
    }
    fun getDataInMain2(temp:Int){
        val retrofit=Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val testService=retrofit.create(FirstService::class.java)
        testService.getInternetData2(temp).enqueue(object :Callback<FirstData>{
            override fun onResponse(call: Call<FirstData>, response: Response<FirstData>) {
                val data=response.body()
                if (data!=null){
                    storyLIfeData.postValue(data.stories)
                }
            }
            override fun onFailure(call: Call<FirstData>, t: Throwable) {
            }
        }
        )
    }
    fun getDataInAssess(temp:Int?){
        val retrofit=Retrofit.Builder()
            .baseUrl( "http://news-at.zhihu.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val testService=retrofit.create(FirstService::class.java)
        testService.getLongAssessData(temp).enqueue(object :Callback<AssessData>{
            override fun onResponse(call: Call<AssessData>, response: Response<AssessData>) {
                val data=response.body()
                if (data != null) {
                    assessLIfeData.postValue(data.comments)
                }
            }
            override fun onFailure(call: Call<AssessData>, t: Throwable) {
            }
        }
        )
    }
}