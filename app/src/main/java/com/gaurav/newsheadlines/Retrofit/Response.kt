package com.gaurav.newsheadlines


import com.gaurav.newsheadlines.Model.Result
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=tesla&from=2021-05-14&sortBy=publishedAt&apiKey=1581ff1cec8d43bc8618aec2cbdcc603
const val BASEURL = "https://newsapi.org/"
interface Response {


    @GET("v2/everything/?from=2021-05-14&sortBy=publishedAt&apiKey=1581ff1cec8d43bc8618aec2cbdcc603")
    fun getnews(@Query("q")term:String): Call<Result>
}

object news{
    val result: Response
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        result= retrofit.create(Response::class.java)
    }

}