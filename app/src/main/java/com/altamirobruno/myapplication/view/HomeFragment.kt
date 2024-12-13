package com.altamirobruno.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.presentantion.HomePresenter
import com.altamirobruno.myapplication.view.CategoryAdapter


class HomeFragment : Fragment() {
    private lateinit var presenter: HomePresenter
    private lateinit var adapter: CategoryAdapter

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
        presenter.loadingCategories()
        rv_category.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun showMovies() {
        adapter.notifyDataSetChanged()
        Log.d("chegou", presenter.categories.toString())

    }


}