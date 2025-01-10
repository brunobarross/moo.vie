package com.altamirobruno.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altamirobruno.myapplication.databinding.FragmentHomeBinding
import com.altamirobruno.myapplication.presentantion.HomePresenter
import com.altamirobruno.myapplication.view.CategoryAdapter


class HomeFragment : Fragment() {
    private lateinit var presenter: HomePresenter
    private lateinit var adapter: CategoryAdapter
    private lateinit var progressBar: ProgressBar
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_item)
        val rv_category: RecyclerView = binding.rvCategory
        rv_category.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategoryAdapter(presenter.categories, this)
        rv_category.adapter = adapter
        val noHaveMovies = presenter.categories.all { category -> category.movies.isEmpty() }
        if (noHaveMovies) {
            presenter.loadingCategories()
        }


        adapter.notifyDataSetChanged()
    }

    fun showMovies() {
        adapter.notifyDataSetChanged()


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