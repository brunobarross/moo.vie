package com.altamirobruno.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.model.MovieResponse
import com.altamirobruno.myapplication.presentantion.HomePresenter
import com.altamirobruno.myapplication.view.MovieAdapter


class HomeFragment : Fragment() {
    private lateinit var presenter: HomePresenter
    private lateinit var rv_home: RecyclerView
    private lateinit var adapter: MovieAdapter
    private var movies = mutableListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_home = view.findViewById(R.id.rv_home)
        rv_home.layoutManager = GridLayoutManager(requireContext(), 3)
        adapter = MovieAdapter(movies, this)
        if (adapter.itemCount === 0) {
            presenter.findAllMovies()
        }
        rv_home.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    fun showMovies(response: List<Movie>) {
        val moviesResponse = response
        movies.addAll(moviesResponse)
        adapter.notifyDataSetChanged()


    }


}