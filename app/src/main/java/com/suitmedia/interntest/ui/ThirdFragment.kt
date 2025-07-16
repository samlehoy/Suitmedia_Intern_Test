package com.suitmedia.interntest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suitmedia.interntest.databinding.FragmentThirdBinding
import com.suitmedia.interntest.helper.UserAdapter
import com.suitmedia.interntest.model.RetrofitClient
import kotlinx.coroutines.launch

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: UserAdapter
    private var currentPage = 1
    private var totalPages = 1
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupRefresh()
        loadUsers()

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // scroll listener untuk pagination
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val layoutManager = rv.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    val isAtEnd = (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    val shouldPaginate = !isLoading && currentPage < totalPages && isAtEnd

                    if (shouldPaginate) {
                        currentPage++
                        loadUsers()
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter { selectedUser ->
            val fullName = "${selectedUser.first_name} ${selectedUser.last_name}"
            setFragmentResult("selectedUser", bundleOf("userName" to fullName))
            findNavController().popBackStack()
        }
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // divider horizontal antar item
        val divider = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.recyclerView.addItemDecoration(divider)
    }

    private fun setupRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            currentPage = 1
            adapter.setData(emptyList())
            loadUsers()
        }
    }

    private fun loadUsers() {
        isLoading = true
        binding.swipeRefreshLayout.isRefreshing = true

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.apiService.getUsers(currentPage, 10)
                if (response.isSuccessful) {
                    response.body()?.let {
                        totalPages = it.total_pages
                        if (currentPage == 1) {
                            adapter.setData(it.data)
                        } else {
                            adapter.addData(it.data)
                        }
                    }
                } else {
                    Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
                isLoading = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
