package com.altamirobruno.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.altamirobruno.myapplication.R
import com.altamirobruno.myapplication.databinding.FragmentMovieBinding
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.Trailer
import com.altamirobruno.myapplication.presentantion.MoviePresenter
import com.bumptech.glide.Glide


class MovieFragment : Fragment() {
    private lateinit var presenter: MoviePresenter
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private var playBtn: ImageView? = null

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
        playBtn = _binding?.iconPlay
        playBtn?.setOnClickListener {
            presenter.onButtonClicked()
        }


    }

    fun hideCover() {
        val movieCover: ImageView = binding.movieImage
        if (movieCover != null) {
            movieCover.visibility = View.INVISIBLE
        }
    }

    fun hidePlayButton() {
        playBtn?.visibility = View.GONE
    }


    fun showMovieDetail(movie: Movie) {
        val movieCover: ImageView? = binding.movieImage
        val movieTitle = binding.movieDetailTitle
        val movieDescrition = binding.movieDetailDescription

        if (movieCover != null) {
            Glide.with(this)
                .load(movie.poster_path)
                .centerCrop()
                .placeholder(R.drawable.movie_cover_placeholder)
                .into(movieCover)
        }
        movieTitle.text = movie.title
        movieDescrition.text = movie.overview
    }

    fun showTrailer(trailer: Trailer) {
        val movieVideo = binding.movieVideo
        val webView = binding.movieVideo
        val video: String =
            "<html><body style='margin:0;padding:0;'><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/${trailer.key}?si=elgeGCV__j_4PBTT&amp;controls=1;autoplay=1\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe></body></html>"
        webView.loadData(video, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        movieVideo.visibility = View.VISIBLE
        hideCover()
    }

    fun showErrorToast(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }


    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)


    }

    override fun onPause() {
        super.onPause()
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(true)
    }


}