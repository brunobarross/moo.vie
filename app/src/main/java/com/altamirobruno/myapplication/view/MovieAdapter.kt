package com.altamirobruno.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.R
import com.altamirobruno.myapplication.model.Movie
import com.bumptech.glide.Glide


class MovieAdapter(
    private val movies: List<Movie>,
    private val fragment: Fragment,
    private val isGrid: Boolean = false,
    private val isSearch: Boolean = false
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


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
            val movieCover: ImageView = itemView.findViewById(R.id.movie_cover)
            val movie_average: TextView = itemView.findViewById(R.id.movie_rating)
            movieTitle.text = movie.title
            val format = R.string.decimal_format
            movie_average.text = fragment.getString(format, movie.vote_average).replace(',', '.')
            if (isGrid) changeSizeCover(itemView)
            Glide
                .with(fragment)
                .load(movie.poster_path)
                .centerCrop()
                .placeholder(R.drawable.movie_cover_placeholder)
                .into(movieCover);

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", movie.id)
                if (isSearch) {
                    fragment.findNavController().navigate(
                        R.id.action_nav_search_to_nav_movie, bundle
                    )
                    return@setOnClickListener

                }
                fragment.findNavController().navigate(
                    R.id.action_nav_home_to_nav_movie, bundle
                )
            }
        }

    }

    fun changeSizeCover(itemView: View) {
        val movieCover: CardView = itemView.findViewById(R.id.card_movie_cover)
        if (itemCount <= 1) return
        movieCover.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        movieCover.requestLayout()

    }

}