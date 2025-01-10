package com.altamirobruno.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.R
import com.altamirobruno.myapplication.databinding.FragmentMovieSearchBinding
import com.altamirobruno.myapplication.presentantion.MovieSearchPresenter

class MovieSearchFragment : Fragment() {
    private lateinit var presenter: MovieSearchPresenter
    private lateinit var adapter: MovieAdapter
    private lateinit var progressBar: ProgressBar
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
        progressBar = view.findViewById(R.id.progress_item_search)
        val rv_movie_search: RecyclerView = binding.rvMovieSearch
        rv_movie_search.layoutManager = GridLayoutManager(requireContext(), 2)
        val input_search = _binding?.inputSearch
        adapter = MovieAdapter(presenter.movies, this, true, true)
        rv_movie_search.adapter = adapter
        input_search?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    val value = input_search.text.toString()
                    handleSearchMovie(value)
                    true
                }

                else -> false
            }


        }


    }

    fun showMovies() {
        _binding?.resultText?.visibility = View.GONE
        _binding?.rvMovieSearch?.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    fun showNotFoundText(query: String) {
        val textNotFound = _binding?.resultText
        textNotFound?.visibility = View.VISIBLE
        _binding?.rvMovieSearch?.visibility = View.GONE
        textNotFound?.text = "Não há resultados para a busca $query"
        adapter.notifyDataSetChanged()

    }

    fun handleSearchMovie(query: String) {
        presenter.loadingMovies(query)
    }


    fun showErrorToast(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}