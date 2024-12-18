package com.altamirobruno.myapplication.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.altamirobruno.myapplication.R
import com.altamirobruno.myapplication.databinding.FragmentHomeBinding
import com.altamirobruno.myapplication.databinding.FragmentMovieBinding
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.presentantion.MoviePresenter
import com.bumptech.glide.Glide


class MovieFragment : Fragment(
) {
    private lateinit var presenter: MoviePresenter
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MoviePresenter(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: Int = arguments?.getInt("id") ?: 0
        presenter.loadingMovie(id)
        presenter.loadingTrailer(id)

    }

    fun showMovieDetail(movie: Movie) {
//        val movieCover: ImageView? = binding.movieImage
        val movieTitle = binding.movieDetailTitle
        val movieDescrition = binding.movieDetailDescription
        val movieVideo = binding.movieVideo
//        if (movieCover != null) {
//
//            Glide
//                .with(this)
//                .load(movie.poster_path)
//                .centerCrop()
//                .placeholder(R.drawable.movie_cover_placeholder)
//                .into(movieCover)
//        };
        val uriParsed = Uri.parse("https://www.youtube.com/watch?v=j1zcmX1XEg4")
        movieVideo.setVideoURI(uriParsed)
        movieTitle.text = movie.title
        movieDescrition.text = movie.overview


    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

    }

    override fun onPause() {
        super.onPause()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }


}