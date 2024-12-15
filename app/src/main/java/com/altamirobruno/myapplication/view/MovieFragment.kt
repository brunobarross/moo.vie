package com.altamirobruno.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.altamirobruno.myapplication.R
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.presentantion.MoviePresenter
import com.bumptech.glide.Glide


class MovieFragment : Fragment(
) {
    private lateinit var presenter: MoviePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MoviePresenter(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: Int = arguments?.getInt("id") ?: 0
        presenter.loadingMovie(id)
    }

    fun showMovieDetail(movie: Movie) {
        val movieCover: ImageView? = view?.findViewById(R.id.movie_image)
        val movieTitle: TextView? = view?.findViewById(R.id.movie_detail_title)
        val movieDescrition: TextView? = view?.findViewById(R.id.movie_detail_description)
        if (movieCover != null) {
            Glide
                .with(this)
                .load(movie.poster_path)
                .centerCrop()
                .placeholder(R.drawable.movie_cover_placeholder)
                .into(movieCover)
        };
        if (movieTitle !== null) movieTitle.text = movie.title
        if(movieDescrition !== null) movieDescrition.text = movie.overview


    }


}