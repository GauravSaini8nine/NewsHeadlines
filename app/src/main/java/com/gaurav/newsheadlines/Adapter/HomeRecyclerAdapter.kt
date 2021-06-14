package com.gaurav.newsheadlines.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.newsheadlines.Model.News
import com.gaurav.newsheadlines.Activity.WebViewActivity
import com.gaurav.newsheadlines.R
import com.squareup.picasso.Picasso


class HomeRecyclerAdapter (var context: Context, val itemList : List<News>)
    :RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>(){
    class HomeViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var cardlayout : CardView = view.findViewById(R.id.cardlayout)


        var author:TextView = view.findViewById(R.id.author)
        var description:TextView = view.findViewById(R.id.description)
        var publish:TextView = view.findViewById(R.id.publish)
        var title:TextView = view.findViewById(R.id.new_title)
        var ImageView:ImageView = view.findViewById(R.id.new_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rmain_ecycler_view_structure,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val text = itemList[position]
        holder.author.text = text.author
        holder.publish.text = text.publishedAt
        holder.title.text = text.title
        holder.description.text= text.description
        Picasso.get().load(text.urlToImage).error(R.drawable.images).into(holder.ImageView)


        holder.cardlayout.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("urlLink", text.url )
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}