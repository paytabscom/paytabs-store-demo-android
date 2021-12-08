package com.example.paytabs_demo_store_android.home.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.paytabs_demo_store_android.R
import com.example.paytabs_demo_store_android.databinding.FragmentHomeBinding
import com.example.paytabs_demo_store_android.base_classes.shouldShow
import com.example.paytabs_demo_store_android.products.model.response.Product
import com.example.paytabs_demo_store_android.products.view.adapter.ProductAdapter
import com.example.paytabs_demo_store_android.products.viewmodel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    private val homeViewModel: ProductsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        homeViewModel.getAllProducts()
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel.productList.observe(viewLifecycleOwner, {
            renderRv(binding.rvBestSelling, getRandomProducts(5, it))

            renderRv(binding.rvOffers, getRandomProducts(4, it))

            renderRv(binding.rvFeatured, getRandomProducts(8, it))

            renderRv(binding.rvForYou, getRandomProducts(6, it))

        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        homeViewModel.networkErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
        })
        homeViewModel.serverErrorLD.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Server Error", Toast.LENGTH_SHORT).show()
        })

        homeViewModel.loading.observe(viewLifecycleOwner, {
            binding.progressDialog.shouldShow(it)
        })
    }

    private fun getRandomProducts(size: Int, it: List<Product>): List<Product> {
        val startIndex1 = Random.nextInt(0, it.size - size)
        val list1 = it.subList(startIndex1, startIndex1 + size)
        return list1
    }

    private fun renderRv(rv: RecyclerView, list: List<Product>) {
        val adapter = ProductAdapter { navigateToDetails(it) }
        adapter.setProducts(list)
        rv.adapter = adapter
    }

    private fun navigateToDetails(Details: String) {

        findNavController().navigate(
            R.id.action_navigation_home_to_product_details_fragment,
            bundleOf("Details" to Details)
        )
    }
}