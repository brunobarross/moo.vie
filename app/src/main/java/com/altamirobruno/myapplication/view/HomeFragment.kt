package com.altamirobruno.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.model.Movie
import com.altamirobruno.myapplication.presentantion.HomePresenter
import com.altamirobruno.myapplication.view.CategoryAdapter


class HomeFragment : Fragment() {
    private lateinit var presenter: HomePresenter
    private lateinit var adapter: CategoryAdapter

    var movies = mutableListOf<Movie>()

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
        val rv_category: RecyclerView = view.findViewById(R.id.rv_category)
        rv_category.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategoryAdapter(presenter.categories, this)
        if (adapter.itemCount === 0 && presenter.categories.isNotEmpty()) {
            presenter.findAllMovies()
        }
        rv_category.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun showMovies(response: List<Movie>) {
        val moviesResponse = response
        movies.addAll(moviesResponse)
        adapter.notifyDataSetChanged()

    }


}