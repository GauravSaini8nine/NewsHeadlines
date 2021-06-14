package com.gaurav.newsheadlines.Activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.newsheadlines.Adapter.HomeRecyclerAdapter
import com.gaurav.newsheadlines.Model.Result
import com.gaurav.newsheadlines.R
import com.gaurav.newsheadlines.connection.ConnectionManager
import com.gaurav.newsheadlines.news
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var progressbar: ProgressBar
    lateinit var mainRecyclerView: RecyclerView
    lateinit var newslinearLayout: LinearLayoutManager
    lateinit var recyclerAdapter: HomeRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressbar = findViewById(R.id.progress)
        progressbar.visibility = View.VISIBLE
        if (ConnectionManager().checkConnectivity(this)) {

            getnews("tesla")
        } else {
            val networdialogbox = AlertDialog.Builder(this)
            networdialogbox.setTitle("Network Error")
            networdialogbox.setMessage("You are not Connected to the network")
            networdialogbox.setPositiveButton("Settings") { text, listner ->
                val settingintent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingintent)

                finish()

            }
            networdialogbox.setNegativeButton("Exit") { text, listner ->
                ActivityCompat.finishAffinity(this)
            }
            networdialogbox.create()
            networdialogbox.show()

        }



        mainRecyclerView = findViewById(R.id.main_recycler_view)
        newslinearLayout = LinearLayoutManager(this)


    }

    fun getnews(name: String) {


        val art = news.result.getnews(name)
        art.enqueue(object : Callback<Result> {


            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                progressbar.visibility = View.GONE
                val result = response.body()
                if (result != null) {

                    recyclerAdapter = HomeRecyclerAdapter(this@MainActivity, result.articles)

                    mainRecyclerView.adapter = recyclerAdapter
                    mainRecyclerView.layoutManager = newslinearLayout


                } else {
                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }


            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}