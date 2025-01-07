package com.altamirobruno.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.databinding.FragmentMovieSearchBinding
import com.altamirobruno.myapplication.presentantion.MovieSearchPresenter

class MovieSearchFragment : Fragment() {
    private lateinit var presenter: MovieSearchPresenter
    private lateinit var adapter: MovieAdapter
    private var _binding: FragmentMovieSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MovieSearchPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv_movie_search: RecyclerView = binding.rvMovieSearch
        rv_movie_search.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = MovieAdapter(presenter.movies, this)
        presenter.loadingMovies()
        rv_movie_search.adapter = adapter
    }

    fun showMovies() {
        adapter.notifyDataSetChanged()
    }

    fun showErrorToast(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }
}