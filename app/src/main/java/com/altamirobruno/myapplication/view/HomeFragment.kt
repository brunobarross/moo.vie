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
import com.altamirobruno.myapplication.presentantion.HomePresenter
import com.altamirobruno.myapplication.view.MovieAdapter


class HomeFragment : Fragment() {
    private lateinit var presenter: HomePresenter
    private lateinit var rv_home: RecyclerView
    private lateinit var adapter: MovieAdapter
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
        adapter = MovieAdapter(
            listOf(
                Movie(
                    id = 1,
                    title = "wqwdasd",
                    poster_path = "adafffff",
                    vote_averge = 9.5
                )
            )
        )
        rv_home = view.findViewById(R.id.rv_home)
        rv_home.layoutManager = GridLayoutManager(requireContext(), 3)
        presenter.findAllMovies()

        rv_home.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    fun showMovies(response: List<Movie>) {
        val movies = response
        Log.d("teste", movies.toString())


    }


}