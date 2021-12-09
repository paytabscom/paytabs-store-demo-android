package com.example.paytabs_demo_store_android.categories.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.categories.view.adapter.CategoriesAdapter
import com.example.paytabs_demo_store_android.categories.viewmodel.CategoriesViewModel
import com.example.paytabs_demo_store_android.databinding.FragmentCategoryBinding
import com.example.paytabs_demo_store_android.base_classes.shouldShow
import dagger.hilt.android.AndroidEntryPoint

//higher order function
//lambda expression
//infix function


@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var binding: FragmentCategoryBinding

    private val adapter = CategoriesAdapter {
        navigateToProducts(it)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater)
        initViewModel()
        initRV()
        viewModel.getCategories()
        observeViewModel()
        return binding.root

    }

    private fun initRV() {
        binding.rvCategories.adapter=adapter
    }

    private fun observeViewModel() {
        viewModel.categoriesList.observe(viewLifecycleOwner, {
            adapter.setCategoriesList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.networkErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
        })
        viewModel.serverErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Server Error", Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            binding.progressBar.shouldShow(it)
        })
    }

    private fun initViewModel() {

    }

    private fun navigateToProducts(categoryName: String) {

        findNavController().navigate(
            R.id.action_navigation_categories_to_products_fragment,
            bundleOf("category" to categoryName)
        )
    }


}