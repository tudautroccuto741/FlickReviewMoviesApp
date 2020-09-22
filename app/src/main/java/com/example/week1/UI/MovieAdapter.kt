package com.example.week1.UI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week1.R
import com.example.week1.veiwModel.Movie

class MovieAdapter(private var listMovies : ArrayList<Movie> = arrayListOf(), private var clickListener: OnItemMovieClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    companion object {
        private const val TYPE_POSTER = 0
        private const val TYPE_VIDEO = 1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_POSTER  -> {
                val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(layoutInflater)
            }
            TYPE_VIDEO -> {
                val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
                VideoViewHolder(layoutInflater, parent.context)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun getItemViewType(position: Int) : Int
    {
        val type = listMovies[position].vote_average
        return when {
            type <= 5 -> TYPE_POSTER
            type > 5 -> TYPE_VIDEO
            else -> TYPE_POSTER
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie :Movie = listMovies[position]
//        (holder as MovieViewHolder).bind(movie)
        holder.apply {
            when (holder)
            {
                is MovieViewHolder -> {
                    holder.bind(listMovies[position])
                }
                is VideoViewHolder -> {
                    holder.bind(listMovies[position])
                }
                else -> throw IllegalArgumentException()
            }
        }
        holder.itemView.setOnClickListener {
            clickListener.onClickItem(movie)
        }
    }



    fun addList(listMovie : ArrayList<Movie>?){
        listMovies.clear()
        listMovies.addAll(listMovie ?: arrayListOf())
        notifyDataSetChanged()
    }


    fun updateSource(list:ArrayList<Movie>?)
    {
        var lastSize= listMovies.size
        if (list != null) {
            listMovies.addAll(list)
        }
        list?.size?.let { notifyItemRangeInserted(lastSize, it) }

    }
}

interface OnItemMovieClickListener{
    fun onClickItem(movie: Movie)
}