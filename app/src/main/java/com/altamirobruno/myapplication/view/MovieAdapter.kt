package com.altamirobruno.myapplication.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.R
import com.altamirobruno.myapplication.model.Movie
import com.bumptech.glide.Glide


class MovieAdapter(
    private val movies: List<Movie>,
    private val fragment: Fragment
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)

    }

    fun openFragment(view: View, movie: Movie) {
        view.setOnClickListener {
            Log.d("sss", movie.id.toString())
        }

    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {

            val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
            val movieCover: ImageView = itemView.findViewById(R.id.movie_cover)
            val posterUrl = "https://image.tmdb.org/t/p/original/${movie.poster_path}"
            movieTitle.text = movie.title

            Glide
                .with(fragment)
                .load(movie.poster_path)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(movieCover);
            openFragment(itemView, movie)

        }

    }

}